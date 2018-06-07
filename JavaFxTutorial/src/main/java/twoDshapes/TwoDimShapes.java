package twoDshapes;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class TwoDimShapes extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        /**
         * {@link Line} представляет линию
         */
        Line line = new Line(100, 25, 500, 25);

        /**
         * {@link Rectangle} представляет прямоугольник
         */
        Rectangle rectangle = new Rectangle(100, 35, 300, 150);

        Rectangle roundedRectangle = new Rectangle(100, 200, 300, 150);
        /**
         * закругляем углы
         * указываем высоту и ширину закругления
         */
        roundedRectangle.setArcHeight(50);
        roundedRectangle.setArcWidth(50);

        /**
         * {@link Circle} представляет круг
         */
        Circle circle = new Circle(200, 420, 60);

        /**
         * {@link Ellipse} представляет эллипс
         */
        Ellipse ellipse = new Ellipse(200, 530, 70, 40);

        /**
         * {@link Polygon} представляет фигуру с n углами
         */
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(
                300.0, 650.0,
                450.0, 750.0,
                300.0, 850.0,
                150.0, 750.0

        );

        /**
         * {@link Polyline} представляет собой ломаную незамкнутую линию
         */
        Polyline polyline = new Polyline();
        polyline.getPoints().addAll(
                500.0, 50.0,
                700.0, 50.0,
                750.0, 150.0,
                700.0, 250.0,
                500.0, 250.0,
                450.0, 150.0
                );


        /**
         * {@link CubicCurve} представлею кривую линию с заштрихованной областью внутри
         * также есть классы {@link QuadCurve} - парабола
         * {@link Arc} - дуга
         * {@link SVGPath} - векторная графика
         */
        CubicCurve cubicCurve = new CubicCurve();

        cubicCurve.setStartX(450.0f);
        cubicCurve.setStartY(350.0f);
        cubicCurve.setControlX1(750.0f);
        cubicCurve.setControlY1(240.0f);
        cubicCurve.setControlX2(525.0f);
        cubicCurve.setControlY2(450.0f);
        cubicCurve.setEndX(850.0f);
        cubicCurve.setEndY(350.0f);

        Group root = new Group();
        ObservableList<Node> list = root.getChildren();
        list.addAll(
                line, rectangle, roundedRectangle,
                circle, ellipse, polygon,
                polyline, cubicCurve
        );


        Scene scene = new Scene(root, 600, 300);
        primaryStage.setTitle("2DimShapes");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
