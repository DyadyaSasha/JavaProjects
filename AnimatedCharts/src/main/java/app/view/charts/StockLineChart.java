package app.view.charts;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.util.StringConverter;
import org.gillius.jfxutils.chart.AxisConstraintStrategies;
import org.gillius.jfxutils.chart.XYChartInfo;
import app.utils.MyChartPanManager;
import app.utils.MyChartZoomManager;
import app.view.CSSStylesNames;


public class StockLineChart extends HBox implements ChartInterface {

    private byte zoomLevel = 0;
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
    private double minVal = 44;
    private double maxVal = 45;
    private double initialTimeUpperBound = 60;
    private double initialTimeLowerBound = 0;
    private double initialPriceUpperBound = 60;
    private double initialPriceLowerBound = 0;
    private double xTick;
    private double yTick;
    private MyChartPanManager chartPanManager;

    public StockLineChart() {

        /**
         * файл с стилями
         */
        final String stockLineChartCss = getClass().getResource("/styles/RootWindow.css").toExternalForm();
        this.getStylesheets().add(stockLineChartCss);
        this.getStyleClass().add(CSSStylesNames.ALL_CHARTS_BOX.getStyleName());

        /**
         * класс, предоставляющий анимацию
         */
        final KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), event -> plotTime());
        chartStockAnimation = new Timeline();
        chartStockAnimation.getKeyFrames().add(keyFrame);
        chartStockAnimation.setCycleCount(Animation.INDEFINITE);


        /**
         * описываем оси графика и сам график
         */
        xTick = (initialTimeUpperBound - initialTimeLowerBound) / 60;
        xAxis = new NumberAxis(initialTimeLowerBound, initialTimeUpperBound, xTick);
        yAxis = new NumberAxis(initialPriceLowerBound, initialPriceUpperBound, 10);
////      в MyChartPanManager и MyChartZoomManager каждый раз вызываются данные методы(там они закомментированы)
        xAxis.setAutoRanging(false);
        yAxis.setAutoRanging(false);


////        xAxis.setForceZeroInRange(false);

        yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis, "$", null));
        yAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return String.format("$%.0f", object.doubleValue());
            }

            @Override
            public Number fromString(String string) {
////                return Double.parseDouble(string);
                return 0;
            }
        });

        xAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return String.format("%.0f", object.doubleValue());
            }

            @Override
            public Number fromString(String string) {
////                return Double.parseDouble(string);
                return 0;
            }
        });

        dataSeries = new XYChart.Series<>();
        dotSeries = new XYChart.Series<>();


        chart = new LineChart<Number, Number>(xAxis, yAxis);
        chart.setAnimated(false);
        chart.getStyleClass().add(CSSStylesNames.STOCK_CHART.getStyleName());
        chart.getData().addAll(dataSeries, dotSeries);


////        TODO: реализовать метод для покупки и продажи(ниже прелставлен пример реализации рисования индикатора-точки покупки/продажи)
////        chart.setOnMouseClicked(event -> {
////            if(event.getButton() != MouseButton.PRIMARY) {
////                return;
////            }
////            Point2D pointInScene = new Point2D(event.getSceneX(), event.getSceneY());
////            double yPosInAxis = yAxis.sceneToLocal(new Point2D(0, pointInScene.getY())).getY();
////            double xPosInAxis = xAxis.sceneToLocal(new Point2D(pointInScene.getX(), 0)).getX();
////            dotSeries.getData().add(new XYChart.Data<>(xAxis.getValueForDisplay(xPosInAxis),yAxis.getValueForDisplay(yPosInAxis)));
////        });


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

        chartPanManager = new MyChartPanManager(this);
        chartPanManager.setAxisConstraintStrategy(AxisConstraintStrategies.getIgnoreOutsideChart());
        chartPanManager.setMouseFilter(mouseEvent -> {
            if (mouseEvent.getButton() != MouseButton.PRIMARY) mouseEvent.consume();
        });
        chartPanManager.start();

        pane = new Pane();
        pane.getStyleClass().add(CSSStylesNames.STOCK_CHART_PANE.getStyleName());


        Rectangle zoomRectangle = new Rectangle(0, 0, Color.LIGHTSEAGREEN.deriveColor(0, 1, 1, 0.5));
        zoomRectangle.getStyleClass().add(CSSStylesNames.ZOOM_RECTANGLE.getStyleName());
        MyChartZoomManager zoomManager = new MyChartZoomManager(pane, zoomRectangle, this);
        zoomManager.setAxisConstraintStrategy(AxisConstraintStrategies.getIgnoreOutsideChart());
        zoomManager.setMouseWheelAxisConstraintStrategy(AxisConstraintStrategies.getIgnoreOutsideChart());
        zoomManager.setMouseFilter(mouseEvent -> {
            if (mouseEvent.getButton() != MouseButton.SECONDARY) mouseEvent.consume();
        });
        chartInfo = zoomManager.getChartInfo();
        zoomManager.start();


//        /**
//         * линии и надписи для креста на графике
//         */
        horizontalLine = new Line();
        verticalLine = new Line();
        horizontalLine.getStyleClass().add(CSSStylesNames.CROSS_VALUE_MARKER.getStyleName());
        verticalLine.getStyleClass().add(CSSStylesNames.CROSS_VALUE_MARKER.getStyleName());
        horizontalLabel = new Label();
        horizontalLabel.setStyle(CSSStylesNames.CROSS_LABELS.getStyleName());
        verticalLabel = new Label();
        verticalLabel.setStyle(CSSStylesNames.CROSS_LABELS.getStyleName());
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
        VBox boxForAreaCharts = new VBox();
//        boxForAreaCharts.getStyleClass().add(CSSStylesNames.AREA_CHARTS_BOX.getStyleName());

        NumberAxis xAxisArea1 = new NumberAxis();
        NumberAxis yAxisArea1 = new NumberAxis();


        AreaChart<Number, Number> area = new AreaChart<Number, Number>(xAxisArea1, yAxisArea1);
        area.getStyleClass().add(CSSStylesNames.AREA1.getStyleName());


        boxForAreaCharts.getChildren().addAll(area);


//        pane = new MyDemoPane();


        VBox.setVgrow(pane, Priority.ALWAYS);
        HBox.setHgrow(pane, Priority.ALWAYS);
        VBox.setVgrow(this, Priority.ALWAYS);
        HBox.setHgrow(this, Priority.ALWAYS);
        HBox.setHgrow(area, Priority.ALWAYS);
        VBox.setVgrow(area, Priority.ALWAYS);

        chart.prefWidthProperty().bind(pane.widthProperty());
        chart.prefHeightProperty().bind(pane.heightProperty());

//        pane.prefHeightProperty().bind(this.prefHeightProperty());
//        pane.prefWidthProperty().bind(this.prefWidthProperty());

//        area.prefWidthProperty().bind(this.prefWidthProperty().divide(1));
//        area.prefHeightProperty().bind(this.prefHeightProperty());

        this.getChildren().addAll(pane, area);

    }


    private void mouseActionInChartPlot(MouseEvent event) {
//        Bounds bounds = chartBackground.localToScene(chartBackground.getLayoutBounds());
        if(chartInfo.getPlotArea().contains(new Point2D(event.getX(), event.getY()))){
//        if (bounds.contains(event.getSceneX(), event.getSceneY())) {
//        if (event.getSceneX() >= bounds.getMinX() && event.getSceneX() <= bounds.getMaxX() && event.getSceneY() >= bounds.getMinY() && event.getSceneY() <= bounds.getMaxY()) {
            ((Node) event.getSource()).setCursor(Cursor.CROSSHAIR);
            updateLines(event);
            horizontalLine.setVisible(true);
            verticalLine.setVisible(true);
            horizontalLabel.setVisible(true);
            verticalLabel.setVisible(true);
        } else {
            ((Node) event.getSource()).setCursor(Cursor.DEFAULT);
            horizontalLine.setVisible(false);
            verticalLine.setVisible(false);
            horizontalLabel.setVisible(false);
            verticalLabel.setVisible(false);
        }
    }

    private void plotTime() {
        double y = minVal + Math.random() * (maxVal - minVal + 1);
//        ((XYSeriesCollection)((MyDemoPane)pane).getDataset()).getSeries(0).add(prevX, y);
        dataSeries.getData().add(new XYChart.Data<>(prevX, y));
        prevX += 1;
        lastY = y;

        if (prevX >= xAxis.getUpperBound() - 6*this.xTick && !chartPanManager.isPanning()) {
            xAxis.setUpperBound(xAxis.getUpperBound() + 1);
            xAxis.setLowerBound(xAxis.getLowerBound() + 1);
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

//        Bounds chartBounds = chart.localToScene(chart.getLayoutBounds());
//        Bounds chartBounds = chart.getLayoutBounds();
//        Bounds chartAreaBounds = chartBackground.localToScene(chartBackground.getLayoutBounds());
//        Bounds chartAreaBounds = chartBackground.getLayoutBounds();

        Rectangle2D plotArea = chartInfo.getPlotArea();
        double xMouse = event.getX();
        double yMouse = event.getY();
        double plotMinX = plotArea.getMinX();
        double plotMaxX = plotArea.getMaxX();
        double plotMinY = plotArea.getMinY();
        double plotMaxY = plotArea.getMaxY();



        horizontalLine.setStartY(yMouse);
//        horizontalLine.setStartX(chartAreaBounds.getMinX());
//        horizontalLine.setStartX(chart.localToParent(chartBackground.localToParent(chartBackground.getLayoutBounds())).getMinX());
        horizontalLine.setStartX(plotMinX);
        horizontalLine.setEndX(plotMaxX);
        horizontalLine.setEndY(yMouse);
//        horizontalLine.setEndX(chart.localToParent(chartBackground.localToParent(chartBackground.getLayoutBounds())).getMaxX());
//        horizontalLine.setEndX(chartAreaBounds.getMaxX());

        Point2D pointInScene = new Point2D(event.getSceneX(), event.getSceneY());


//        horizontalLabel.setLayoutX(chartAreaBounds.getMinX() - horizontalLabel.getWidth());
        horizontalLabel.setLayoutX(plotMinX - horizontalLabel.getWidth());
        horizontalLabel.setLayoutY(yMouse - horizontalLabel.getHeight() / 2);
        double yPosInAxis = yAxis.sceneToLocal(new Point2D(0, pointInScene.getY())).getY();
        horizontalLabel.setText(String.format("%.2f", (double) yAxis.getValueForDisplay(yPosInAxis)));


//        verticalLine.setStartY(chartAreaBounds.getMaxY() - (chartBounds.getMaxY() - chartAreaBounds.getMaxY()));
//        verticalLine.setStartY(chart.localToParent(chartBackground.localToParent(chartBackground.getBoundsInParent())).getMaxY()-3);
        verticalLine.setStartY(plotMinY);
        verticalLine.setStartX(xMouse);
//        verticalLine.setEndY(chart.localToParent(chartBackground.localToParent(chartBackground.getBoundsInParent())).getMinY()-3);
        verticalLine.setEndY(plotMaxY);
//        verticalLine.setEndY(chartAreaBounds.getMinY() - chartBounds.getMinY());
        verticalLine.setEndX(xMouse);


        verticalLabel.setLayoutX(xMouse - verticalLabel.getWidth() / 2);

//        verticalLabel.setLayoutY(chart.localToParent(chartBackground.localToParent(chartBackground.getBoundsInParent())).getMaxY());
        verticalLabel.setLayoutY(plotMaxY + 0.9);


//        System.out.println(pane.localToParent(pane.getBoundsInLocal()));
//        System.out.println(pane.localToParent(chart.localToParent(chartBackground.localToParent(chartBackground.getBoundsInLocal()))));


////        verticalLabel.setLayoutY(chartAreaBounds.getMaxY() - (chartBounds.getMaxY() - chartAreaBounds.getMaxY()));
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

    public double getXTick() {
        return xTick;
    }

    public XYChart.Series<Number, Number> getDataSeries() { return dataSeries; }

    public void setXTick(double xTick) {
        this.xTick = xTick;
    }

    public double getYTick() {
        return yTick;
    }

    public void setYTick(double yTick) {
        this.yTick = yTick;
    }

    public byte getZoomLevel() {
        return zoomLevel;
    }

    public void setZoomLevel(byte zoomLevel) {
        this.zoomLevel = zoomLevel;
    }
}


