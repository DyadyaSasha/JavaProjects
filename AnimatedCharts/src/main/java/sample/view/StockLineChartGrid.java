package sample.view;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.gillius.jfxutils.chart.AxisConstraintStrategies;
import org.gillius.jfxutils.chart.XYChartInfo;
import sample.utils.MyChartPanManager;
import sample.utils.MyChartZoomManager;

public class StockLineChartGrid  extends GridPane{
    private LineChart<Number, Number> chart;
    private Pane pane;
    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private Node chartBackground;
    private Line horizontalLine;
    private Line verticalLine;
    private Label horizontalLabel;
    private Label verticalLabel;
    private XYChart.Series<Number, Number> dataSeries;
    private XYChart.Series<Number, Number> dotSeries;
    private XYChartInfo chartInfo;
    private Timeline chartStockAnimation;
    private double prevX = 0;
    private double lastY = 0;
    private double minVal = 40;
    private double maxVal = 50;
    private double initialTimeUpperBound = 60;
    private double initialTimeLowerBound = 0;
    private double initialPriceUpperBound = 60;
    private double initialPriceLowerBound = 0;

    public StockLineChartGrid() {

        /**
         * файл с стилями
         */
        final String stockLineChartCss = getClass().getResource("/styles/StockLineChart.css").toExternalForm();
        this.getStylesheets().add(stockLineChartCss);
        this.getStyleClass().add(CSSStylesNames.ALL_CHARTS_BOX.getStyleName());

        /**
         * класс, предоставляющий анимацию
         */
        final KeyFrame keyFrame = new KeyFrame(Duration.millis(AppConstants.CHART_REFRESH_DURATION), event -> plotTime());
        chartStockAnimation = new Timeline();
        chartStockAnimation.getKeyFrames().add(keyFrame);
        chartStockAnimation.setCycleCount(Animation.INDEFINITE);


        /**
         * описываем оси графика и сам график
         */
        xAxis = new NumberAxis(initialTimeLowerBound, initialTimeUpperBound, 10);
        yAxis = new NumberAxis(initialPriceLowerBound, initialPriceUpperBound, 10);

//        xAxis.setForceZeroInRange(false);

        //TODO: сделать форматирование данных на осях
        yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis, "$", null));
//        yAxis.setTickLabelFormatter(new StringConverter<Number>() {
//            @Override
//            public String toString(Number object) {
//                return String.format("$%.0f", object.doubleValue());
//            }
//
//            @Override
//            public Number fromString(String string) {
//                return Double.parseDouble(string);
//            }
//        });
//
//        xAxis.setTickLabelFormatter(new StringConverter<Number>() {
//            @Override
//            public String toString(Number object) {
//                return String.format("%.0f", object.doubleValue());
//            }
//
//            @Override
//            public Number fromString(String string) {
//                return Double.parseDouble(string);
//            }
//        });

        dataSeries = new XYChart.Series<>();
        dotSeries = new XYChart.Series<>();


        chart = new LineChart<Number, Number>(xAxis, yAxis);
        chart.getStyleClass().add(CSSStylesNames.STOCK_CHART.getStyleName());
        chart.getData().addAll(dataSeries, dotSeries);

        chart.setAnimated(false);

//        TODO: реализовать метод для покупки и продажи(ниже прелставлен пример реализации рисования индикатора-точки покупки/продажи)
//        chart.setOnMouseClicked(event -> {
//            if(event.getButton() != MouseButton.PRIMARY) {
//                return;
//            }
//            Point2D pointInScene = new Point2D(event.getSceneX(), event.getSceneY());
//            double yPosInAxis = yAxis.sceneToLocal(new Point2D(0, pointInScene.getY())).getY();
//            double xPosInAxis = xAxis.sceneToLocal(new Point2D(pointInScene.getX(), 0)).getX();
//            dotSeries.getData().add(new XYChart.Data<>(xAxis.getValueForDisplay(xPosInAxis),yAxis.getValueForDisplay(yPosInAxis)));
//        });


        chart.setOnMouseMoved(this::mouseActionInChartPlot);

        chart.setOnMouseDragged(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                mouseActionInChartPlot(event);

            } else {
                if (event.getButton() == MouseButton.SECONDARY) {
                    mouseActionInChartPlot(event);
                    ((Node) event.getSource()).setCursor(Cursor.CLOSED_HAND);
                    updateLines(event);
                }
            }
        });


        chart.setOnMouseExited(event -> {
            horizontalLabel.setVisible(false);
            verticalLabel.setVisible(false);
            horizontalLine.setVisible(false);
            verticalLine.setVisible(false);
        });

        chart.setOnMouseReleased(event -> {
            if (event.getButton() == MouseButton.SECONDARY && ((Node) event.getSource()).getCursor() == Cursor.CLOSED_HAND) {
                ((Node) event.getSource()).setCursor(Cursor.DEFAULT);
            }
        });

        MyChartPanManager chartPanManager = new MyChartPanManager(chart);
        chartPanManager.setAxisConstraintStrategy(AxisConstraintStrategies.getIgnoreOutsideChart());
        chartPanManager.setMouseFilter(mouseEvent -> {
            if (mouseEvent.getButton() != MouseButton.PRIMARY) mouseEvent.consume();
        });
        chartPanManager.start();

        pane = new Pane();
        pane.getStyleClass().add(CSSStylesNames.STOCK_CHART_PANE.getStyleName());

        chart.prefWidthProperty().bind(pane.widthProperty());
        chart.prefHeightProperty().bind(pane.heightProperty());

        Rectangle zoomRectangle = new Rectangle(0, 0, Color.LIGHTSEAGREEN.deriveColor(0, 1, 1, 0.5));
        zoomRectangle.getStyleClass().add(CSSStylesNames.ZOOM_RECTANGLE.getStyleName());
        MyChartZoomManager zoomManager = new MyChartZoomManager(pane, zoomRectangle, chart);
        zoomManager.setAxisConstraintStrategy(AxisConstraintStrategies.getIgnoreOutsideChart());
        zoomManager.setMouseWheelAxisConstraintStrategy(AxisConstraintStrategies.getIgnoreOutsideChart());
        zoomManager.setMouseFilter(mouseEvent -> {
            if (mouseEvent.getButton() != MouseButton.SECONDARY) mouseEvent.consume();
        });
        chartInfo = zoomManager.getChartInfo();
        zoomManager.start();


        /**
         * линии и надписи для креста на графике
         */
        horizontalLine = new Line();
        verticalLine = new Line();
        horizontalLine.getStyleClass().add(CSSStylesNames.CROSS_VALUE_MARKER.getStyleName());
        verticalLine.getStyleClass().add(CSSStylesNames.CROSS_VALUE_MARKER.getStyleName());
        horizontalLabel = new Label();
        verticalLabel = new Label();
        horizontalLabel.getStyleClass().addAll(CSSStylesNames.CROSS_VALUE_MARKER.getStyleName(),
                CSSStylesNames.CROSS_LABELS.getStyleName());
        verticalLabel.getStyleClass().addAll(CSSStylesNames.CROSS_VALUE_MARKER.getStyleName(),
                CSSStylesNames.CROSS_LABELS.getStyleName());


        /**
         * отключаем взаимодействия мыши с линиями и подписями креста
         */
        horizontalLine.setMouseTransparent(true);
        verticalLine.setMouseTransparent(true);
        horizontalLabel.setMouseTransparent(true);
        verticalLabel.setMouseTransparent(true);

        pane.getChildren().addAll(chart, horizontalLine, verticalLine, verticalLabel, horizontalLabel, zoomRectangle);

        /**
         * холст графика
         */
        chartBackground = chart.lookup(".chart-plot-background");
        chartBackground.setOnMouseEntered(event -> ((Node) event.getSource()).setCursor(Cursor.CROSSHAIR));
        chartBackground.setOnMouseExited(event -> ((Node) event.getSource()).setCursor(Cursor.DEFAULT));

//        VBox boxForAreaCharts = new VBox();
//        boxForAreaCharts.getStyleClass().add(CSSStylesNames.AREA_CHARTS_BOX.getStyleName());

        NumberAxis xAxisArea1 = new NumberAxis();
        NumberAxis yAxisArea1 = new NumberAxis();




        AreaChart<Number, Number> area1 = new AreaChart<Number, Number>(xAxisArea1, yAxisArea1);
        area1.getStyleClass().add(CSSStylesNames.AREA1.getStyleName());


//        boxForAreaCharts.getChildren().addAll(area1);


//        VBox.setVgrow(chart, Priority.ALWAYS);
//        HBox.setHgrow(chart, Priority.ALWAYS);
//        VBox.setVgrow(pane, Priority.ALWAYS);
//        HBox.setHgrow(pane, Priority.ALWAYS);
//        HBox.setHgrow(area1, Priority.ALWAYS);
//        VBox.setVgrow(area1, Priority.ALWAYS);


//        pane.prefHeightProperty().bind(this.prefHeightProperty());
//        pane.prefWidthProperty().bind(this.prefWidthProperty().multiply(1.5));
//        pane.setMinSize(500,200);

//        boxForAreaCharts.prefWidthProperty().bind(this.prefWidthProperty().divide(1.5));
//        boxForAreaCharts.prefHeightProperty().bind(this.prefHeightProperty());

//        area1.prefWidthProperty().bind(this.prefWidthProperty().divide(1.5));
//        area1.prefHeightProperty().bind(this.prefHeightProperty());
        HBox.setHgrow(this, Priority.ALWAYS);
        VBox.setVgrow(this, Priority.ALWAYS);
        this.setStyle("-fx-border-color: red");
        this.getChildren().addAll(pane, area1);
        GridPane.setColumnIndex(pane, 0);
        GridPane.setRowIndex(pane, 0);
        GridPane.setColumnIndex(area1, 1);
        GridPane.setRowIndex(area1, 0);
        GridPane.setHgrow(area1, Priority.ALWAYS);
        GridPane.setHgrow(pane, Priority.ALWAYS);
        GridPane.setVgrow(area1, Priority.ALWAYS);
        GridPane.setVgrow(pane, Priority.ALWAYS);

    }


    private void mouseActionInChartPlot(MouseEvent event) {
        Bounds bounds = chartBackground.localToScene(chartBackground.getLayoutBounds());
        if (event.getSceneX() >= bounds.getMinX() && event.getSceneX() <= bounds.getMaxX() && event.getSceneY() >= bounds.getMinY() && event.getSceneY() <= bounds.getMaxY()) {
            updateLines(event);
            horizontalLine.setVisible(true);
            verticalLine.setVisible(true);
            horizontalLabel.setVisible(true);
            verticalLabel.setVisible(true);
        } else {
            horizontalLine.setVisible(false);
            verticalLine.setVisible(false);
            horizontalLabel.setVisible(false);
            verticalLabel.setVisible(false);
        }
    }

    private void plotTime() {
        double y = minVal + Math.random() * (maxVal - minVal + 1);
        dataSeries.getData().add(new XYChart.Data<>(prevX, y));
        prevX += 0.3;
        lastY = y;
        if (prevX > xAxis.getUpperBound()) {
            xAxis.setUpperBound(xAxis.getUpperBound() + 0.3);
            xAxis.setLowerBound(xAxis.getLowerBound() + 0.3);
        }

//        dataSeries.getNode().setOnMouseEntered(event -> {
//            updateLines(event);
//            horizontalLine.setVisible(true);
//            verticalLine.setVisible(true);
//
//            ((Node) event.getSource()).setCursor(Cursor.HAND);
//        });
//        dataSeries.getNode().setOnMouseExited(event -> ((Node) event.getSource()).setCursor(Cursor.DEFAULT));
    }

    private void updateLines(MouseEvent event) {

        Bounds chartBounds = chart.localToScene(chart.getLayoutBounds());
//        Bounds chartBounds = chart.getLayoutBounds();
        Bounds chartAreaBounds = chartBackground.localToScene(chartBackground.getLayoutBounds());
//        Bounds chartAreaBounds = chartBackground.getLayoutBounds();


        horizontalLine.setStartY(event.getY());
//        horizontalLine.setStartX(chartAreaBounds.getMinX());
//        horizontalLine.setStartX(chart.localToParent(chartBackground.localToParent(chartBackground.getLayoutBounds())).getMinX());
        horizontalLine.setStartX(chartInfo.getPlotArea().getMinX());
        horizontalLine.setEndX(chartInfo.getPlotArea().getMaxX());
        horizontalLine.setEndY(event.getY());
//        horizontalLine.setEndX(chart.localToParent(chartBackground.localToParent(chartBackground.getLayoutBounds())).getMaxX());
//        horizontalLine.setEndX(chartAreaBounds.getMaxX());

        Point2D pointInScene = new Point2D(event.getSceneX(), event.getSceneY());


//        horizontalLabel.setLayoutX(chartAreaBounds.getMinX() - horizontalLabel.getWidth());
        horizontalLabel.setLayoutX(chartInfo.getPlotArea().getMinX() - horizontalLabel.getWidth());
        horizontalLabel.setLayoutY(event.getY() - horizontalLabel.getHeight() / 2);
        double yPosInAxis = yAxis.sceneToLocal(new Point2D(0, pointInScene.getY())).getY();
        horizontalLabel.setText(String.format("%.2f", (double) yAxis.getValueForDisplay(yPosInAxis)));


//        verticalLine.setStartY(chartAreaBounds.getMaxY() - (chartBounds.getMaxY() - chartAreaBounds.getMaxY()));
//        verticalLine.setStartY(chart.localToParent(chartBackground.localToParent(chartBackground.getBoundsInParent())).getMaxY()-3);
        verticalLine.setStartY(chartInfo.getPlotArea().getMinY());
        verticalLine.setStartX(event.getX());
//        verticalLine.setEndY(chart.localToParent(chartBackground.localToParent(chartBackground.getBoundsInParent())).getMinY()-3);
        verticalLine.setEndY(chartInfo.getPlotArea().getMaxY());
//        verticalLine.setEndY(chartAreaBounds.getMinY() - chartBounds.getMinY());
        verticalLine.setEndX(event.getX());


        verticalLabel.setLayoutX(event.getSceneX() - verticalLabel.getWidth() / 2);

//        verticalLabel.setLayoutY(chart.localToParent(chartBackground.localToParent(chartBackground.getBoundsInParent())).getMaxY());
        verticalLabel.setLayoutY(chartInfo.getPlotArea().getMaxY()+0.9);


//        System.out.println(pane.localToParent(pane.getBoundsInLocal()));
//        System.out.println(pane.localToParent(chart.localToParent(chartBackground.localToParent(chartBackground.getBoundsInLocal()))));


//        verticalLabel.setLayoutY(chartAreaBounds.getMaxY() - (chartBounds.getMaxY() - chartAreaBounds.getMaxY()));
        double xPosInAxis = xAxis.sceneToLocal(new Point2D(pointInScene.getX(), 0)).getX();
        verticalLabel.setText(String.format("%.2f", (double) xAxis.getValueForDisplay(xPosInAxis)));
    }

    public void play() {
        chartStockAnimation.play();
    }

    public void stop() {
        chartStockAnimation.stop();
    }

    public double getInitialTimeUpperBound() {
        return initialTimeUpperBound;
    }

    public double getInitialTimeLowerBound() {
        return initialTimeLowerBound;
    }

    public double getInitialPriceUpperBound() {
        return initialPriceUpperBound;
    }

    public double getInitialPriceLowerBound() {
        return initialPriceLowerBound;
    }

    public LineChart<Number, Number> getChart() {
        return chart;
    }

    public double getPrevX() {
        return prevX;
    }

    public double getLastY() {
        return lastY;
    }

}
