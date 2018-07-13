package examples;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.NumberAxis.DefaultFormatter;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.gillius.jfxutils.chart.AxisConstraintStrategies;
import sample.utils.MyChartPanManager;
import sample.utils.MyChartZoomManager;
import sample.view.CSSStylesNames;


public class StockLineChartApp extends Application {

    private Pane pane;
    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private Node chartBackground;
    private Line horizontalLine;
    private Line verticalLine;
    private Label horizontalLabel;
    private Label verticalLabel;
    private XYChart.Series<Number, Number> dataSeries;
    private Timeline animation;
    private double prevX = 0;
    private double minVal = 10;
    private double maxVal = 100;


    public StockLineChartApp() {

        final KeyFrame frame =
                new KeyFrame(Duration.millis(300),
                        (ActionEvent actionEvent) -> {
                            plotTime();

                        });

        animation = new Timeline();
        animation.getKeyFrames().add(frame);
        animation.setCycleCount(Animation.INDEFINITE);
    }

    public Parent createContent() {

        xAxis = new NumberAxis(0, 60, 0.3);
        yAxis = new NumberAxis(0, 100, 10);

        LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);

        horizontalLine = new Line();
        verticalLine = new Line();
        horizontalLine.getStyleClass().add(CSSStylesNames.CROSS_VALUE_MARKER.getStyleName());
        verticalLine.getStyleClass().add(CSSStylesNames.CROSS_VALUE_MARKER.getStyleName());
        horizontalLabel = new Label();
        verticalLabel = new Label();
        horizontalLabel.getStyleClass().add(CSSStylesNames.CROSS_VALUE_MARKER.getStyleName());
        verticalLabel.getStyleClass().add(CSSStylesNames.CROSS_VALUE_MARKER.getStyleName());

        /**
         * отключаем взаимодействия мыши с линиями и подписями
         */
        horizontalLine.setMouseTransparent(true);
        verticalLine.setMouseTransparent(true);
        horizontalLabel.setMouseTransparent(true);
        verticalLabel.setMouseTransparent(true);

        chartBackground = chart.lookup(".chart-plot-background");
        for (Node n : chartBackground.getParent().getChildrenUnmodifiable()) {
            if (n != chartBackground && n != xAxis && n != yAxis) {
//                n.setMouseTransparent(true);
            }
        }




        chart.setOnMouseMoved(event -> {

            Bounds bounds = chartBackground.localToScene(chartBackground.getLayoutBounds());
            if (event.getX() >= bounds.getMinX() && event.getX() <= bounds.getMaxX() && event.getY() >= bounds.getMinY() && event.getY() <= bounds.getMaxY()) {
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
        });

        chart.setOnMouseDragged(event -> {

            if (event.getButton() == MouseButton.SECONDARY) {
                ((Node) event.getSource()).setCursor(Cursor.CLOSED_HAND);
                Bounds bounds = chartBackground.localToScene(chartBackground.getLayoutBounds());
                if (event.getX() >= bounds.getMinX() && event.getX() <= bounds.getMaxX() && event.getY() >= bounds.getMinY() && event.getY() <= bounds.getMaxY()) {
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
        });


        /**
         * как вариант сокрытия крестика
         */
        chart.setOnMouseExited(event -> {
            horizontalLine.setVisible(false);
            verticalLine.setVisible(false);
        });

        chart.setOnMouseReleased(event -> {
            if (event.getButton() == MouseButton.SECONDARY){
                ((Node)event.getSource()).setCursor(Cursor.DEFAULT);
            }
        });

//        chartBackground.setOnMouseEntered(event -> {
////            System.out.println(event.getSource());
//            horizontalLine.setVisible(true);
//            verticalLine.setVisible(true);
//        });

//        chartBackground.setOnMouseExited(event -> {
//            horizontalLine.setVisible(false);
//            verticalLine.setVisible(false);
//        });

//        chartBackground.setOnMouseDragged(event -> {
//
//            if (event.getButton() == MouseButton.SECONDARY) {
//                updateLines(event);
//            }
//
//        });

        MyChartPanManager chartPanManager = new MyChartPanManager(chart);
        chartPanManager.setAxisConstraintStrategy(AxisConstraintStrategies.getIgnoreOutsideChart());
        chartPanManager.setMouseFilter(mouseEvent -> {
            if (mouseEvent.getButton() != MouseButton.SECONDARY) mouseEvent.consume();
        });
        chartPanManager.start();





        final String stockLineChartCss =
                getClass().getResource("styles/StockLineChart.css").toExternalForm();
        chart.getStylesheets().add(stockLineChartCss);
        chart.getStyleClass().add(CSSStylesNames.STOCK_CHART.getStyleName());
        chart.setCreateSymbols(false);
        chart.setAnimated(false);
        chart.setLegendVisible(false);
        xAxis.setForceZeroInRange(false);
        yAxis.setTickLabelFormatter(new DefaultFormatter(yAxis, "$", null));
        xAxis.setTickMarkVisible(false);
        yAxis.setTickMarkVisible(false);
        yAxis.setMinorTickVisible(false);
        xAxis.setMinorTickVisible(false);
        dataSeries = new XYChart.Series();

        chart.getData().add(dataSeries);

        pane = new Pane();
        pane.getStylesheets().add(stockLineChartCss);
        Rectangle zoomRectangle = new Rectangle(0,0, Color.LIGHTSEAGREEN.deriveColor(0, 1, 1, 0.5));
        pane.getChildren().addAll(chart, verticalLine, horizontalLine, verticalLabel, horizontalLabel, zoomRectangle);

        MyChartZoomManager zoomManager = new MyChartZoomManager(pane, zoomRectangle, chart);
        zoomManager.setAxisConstraintStrategy(AxisConstraintStrategies.getIgnoreOutsideChart());
        zoomManager.setMouseWheelAxisConstraintStrategy(AxisConstraintStrategies.getIgnoreOutsideChart());
        zoomManager.start();

        return pane;
    }

    private void plotTime() {
        double y = minVal + Math.random() * (maxVal - minVal + 1);
        dataSeries.getData().add(new Data<>(prevX, y));
        prevX += 0.3;

        dataSeries.getNode().setOnMouseEntered(event -> {
//            System.out.println((event.getSource()));
            updateLines(event);
            horizontalLine.setVisible(true);
            verticalLine.setVisible(true);

            ((Node) event.getSource()).setCursor(Cursor.HAND);
        });
        dataSeries.getNode().setOnMouseExited(event -> ((Node) event.getSource()).setCursor(Cursor.DEFAULT));
    }

    public void play() {
        animation.play();
    }

    @Override
    public void stop() {
        animation.pause();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
        play();
    }

    private void updateLines(MouseEvent event) {

//        Bounds chartAreaBounds = chartBackground.localToScene(chartBackground.getBoundsInLocal());
        Bounds chartAreaBounds = chartBackground.localToScene(chartBackground.getLayoutBounds());

//        horizontalLine.setStartY(event.getY() + chartAreaBounds.getMinY());
        horizontalLine.setStartY(event.getY());
        horizontalLine.setStartX(chartAreaBounds.getMinX());
//        horizontalLine.setEndY(event.getY() + chartAreaBounds.getMinY());
        horizontalLine.setEndY(event.getY());
        horizontalLine.setEndX(chartAreaBounds.getMaxX());

        Point2D pointInScene = new Point2D(event.getSceneX(), event.getSceneY());

        horizontalLabel.setLayoutX(chartAreaBounds.getMinX());
        horizontalLabel.setLayoutY(event.getY());
        double yPosInAxis = yAxis.sceneToLocal(new Point2D(0, pointInScene.getY())).getY();
        horizontalLabel.setText(String.format("%.2f", (double) yAxis.getValueForDisplay(yPosInAxis)));


        verticalLine.setStartY(chartAreaBounds.getMinY());
//        verticalLine.setStartX(chartAreaBounds.getMinX() + event.getX());
        verticalLine.setStartX(event.getX());
        verticalLine.setEndY(chartAreaBounds.getMaxY());
//        verticalLine.setEndX(chartAreaBounds.getMinX() + event.getX());
        verticalLine.setEndX(event.getX());

        verticalLabel.setLayoutX(event.getX());
        verticalLabel.setLayoutY(chartAreaBounds.getMaxY());
        double xPosInAxis = xAxis.sceneToLocal(new Point2D(pointInScene.getX(), 0)).getX();
        verticalLabel.setText(String.format("%.2f", (double) xAxis.getValueForDisplay(xPosInAxis)));
    }

    public static void main(String[] args) {
        launch(args);
    }
}