package sample.utils;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ValueAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.gillius.jfxutils.EventHandlerManager;
import org.gillius.jfxutils.chart.*;
import sample.view.AppConstants;
import sample.view.ChartInterface;

public class MyChartZoomManager {

    public static final EventHandler<MouseEvent> DEFAULT_FILTER = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            //The ChartPanManager uses this reference, so if behavior changes, copy to users first.
            if (mouseEvent.getButton() != MouseButton.PRIMARY)
                mouseEvent.consume();
        }
    };


    private final SimpleDoubleProperty rectX = new SimpleDoubleProperty();
    private final SimpleDoubleProperty rectY = new SimpleDoubleProperty();
    private final SimpleBooleanProperty selecting = new SimpleBooleanProperty(false);

    private final DoubleProperty zoomDurationMillis = new SimpleDoubleProperty(750.0);
    private final BooleanProperty zoomAnimated = new SimpleBooleanProperty(true);
    private final BooleanProperty mouseWheelZoomAllowed = new SimpleBooleanProperty(true);

    private AxisConstraint zoomMode = AxisConstraint.None;
    private AxisConstraintStrategy axisConstraintStrategy = AxisConstraintStrategies.getIgnoreOutsideChart();
    private AxisConstraintStrategy mouseWheelAxisConstraintStrategy = AxisConstraintStrategies.getDefault();

    private EventHandler<? super MouseEvent> mouseFilter = DEFAULT_FILTER;

    private final EventHandlerManager handlerManager;

    private final Rectangle selectRect;
    private final ValueAxis<?> xAxis;
    private final ValueAxis<?> yAxis;
    private final ChartInterface chartImpl;
    private final XYChartInfo chartInfo;

    private final Timeline zoomAnimation = new Timeline();

    // хранит текущий уровень зумирования zoomLevel = [0;6]
    private byte zoomLevel = 0;

    /**
     * Construct a new ChartZoomManager. See {@link ChartZoomManager} documentation for normal usage.
     *
     * @param chartPane  A Pane which is the ancestor of all arguments
     * @param selectRect A Rectangle whose layoutX/Y makes it line up with the chart
     * @param chartImpl      Chart to manage, where both X and Y axis are a {@link ValueAxis}.
     */
    public MyChartZoomManager(Pane chartPane, Rectangle selectRect, ChartInterface chartImpl) {
        this.selectRect = selectRect;
        XYChart<?,?> chart = chartImpl.getChart();
        this.xAxis = (ValueAxis<?>) chart.getXAxis();
        this.yAxis = (ValueAxis<?>) chart.getYAxis();
        this.chartImpl = chartImpl;
        chartInfo = new XYChartInfo(chart, chartPane);

        handlerManager = new EventHandlerManager(chartPane);

        handlerManager.addEventHandler(false, MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (passesFilter(mouseEvent))
                    onMousePressed(mouseEvent);
            }
        });

        handlerManager.addEventHandler(false, MouseEvent.DRAG_DETECTED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (passesFilter(mouseEvent))
                    onDragStart();
            }
        });

        handlerManager.addEventHandler(false, MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //Don't check filter here, we're either already started, or not
                onMouseDragged(mouseEvent);
            }
        });

        handlerManager.addEventHandler(false, MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //Don't check filter here, we're either already started, or not
                onMouseReleased();
            }
        });

        handlerManager.addEventHandler(false, ScrollEvent.ANY, new MyChartZoomManager.MouseWheelZoomHandler());
    }

    /**
     * Returns the current strategy in use for mouse drag events.
     *
     * @see #setAxisConstraintStrategy(AxisConstraintStrategy)
     */
    public AxisConstraintStrategy getAxisConstraintStrategy() {
        return axisConstraintStrategy;
    }

    /**
     * Sets the {@link AxisConstraintStrategy} to use for mouse drag events, which determines which axis is allowed for
     * zooming. The default implementation is {@link AxisConstraintStrategies#getIgnoreOutsideChart()}.
     *
     * @see AxisConstraintStrategies
     */
    public void setAxisConstraintStrategy(AxisConstraintStrategy axisConstraintStrategy) {
        this.axisConstraintStrategy = axisConstraintStrategy;
    }

    /**
     * Returns the current strategy in use for mouse wheel events.
     *
     * @see #setMouseWheelAxisConstraintStrategy(AxisConstraintStrategy)
     */
    public AxisConstraintStrategy getMouseWheelAxisConstraintStrategy() {
        return mouseWheelAxisConstraintStrategy;
    }

    /**
     * Sets the {@link AxisConstraintStrategy} to use for mouse wheel events, which determines which axis is allowed for
     * zooming. The default implementation is {@link AxisConstraintStrategies#getDefault()}.
     *
     * @see AxisConstraintStrategies
     */
    public void setMouseWheelAxisConstraintStrategy(AxisConstraintStrategy mouseWheelAxisConstraintStrategy) {
        this.mouseWheelAxisConstraintStrategy = mouseWheelAxisConstraintStrategy;
    }

    /**
     * If true, animates the zoom.
     */
    public boolean isZoomAnimated() {
        return zoomAnimated.get();
    }

    /**
     * If true, animates the zoom.
     */
    public BooleanProperty zoomAnimatedProperty() {
        return zoomAnimated;
    }

    /**
     * If true, animates the zoom.
     */
    public void setZoomAnimated(boolean zoomAnimated) {
        this.zoomAnimated.set(zoomAnimated);
    }

    /**
     * Returns the number of milliseconds the zoom animation takes.
     */
    public double getZoomDurationMillis() {
        return zoomDurationMillis.get();
    }

    /**
     * Returns the number of milliseconds the zoom animation takes.
     */
    public DoubleProperty zoomDurationMillisProperty() {
        return zoomDurationMillis;
    }

    /**
     * Sets the number of milliseconds the zoom animation takes.
     */
    public void setZoomDurationMillis(double zoomDurationMillis) {
        this.zoomDurationMillis.set(zoomDurationMillis);
    }

    /**
     * If true, allow zooming via mouse wheel.
     */
    public boolean isMouseWheelZoomAllowed() {
        return mouseWheelZoomAllowed.get();
    }

    /**
     * If true, allow zooming via mouse wheel.
     */
    public BooleanProperty mouseWheelZoomAllowedProperty() {
        return mouseWheelZoomAllowed;
    }

    /**
     * If true, allow zooming via mouse wheel.
     */
    public void setMouseWheelZoomAllowed(boolean allowed) {
        mouseWheelZoomAllowed.set(allowed);
    }

    /**
     * Returns the mouse filter.
     *
     * @see #setMouseFilter(EventHandler)
     */
    public EventHandler<? super MouseEvent> getMouseFilter() {
        return mouseFilter;
    }

    /**
     * Sets the mouse filter for starting the zoom action. If the filter consumes the event with
     * {@link Event#consume()}, then the event is ignored. If the filter is null, all events are
     * passed through. The default filter is {@link #DEFAULT_FILTER}.
     */
    public void setMouseFilter(EventHandler<? super MouseEvent> mouseFilter) {
        this.mouseFilter = mouseFilter;
    }

    /**
     * Start managing zoom management by adding event handlers and bindings as appropriate.
     */
    public void start() {
        handlerManager.addAllHandlers();

        selectRect.widthProperty().bind(rectX.subtract(selectRect.translateXProperty()));
        selectRect.heightProperty().bind(rectY.subtract(selectRect.translateYProperty()));
        selectRect.visibleProperty().bind(selecting);
    }

    /**
     * Stop managing zoom management by removing all event handlers and bindings, and hiding the
     * rectangle.
     */
    public void stop() {
        handlerManager.removeAllHandlers();
        selecting.set(false);
        selectRect.widthProperty().unbind();
        selectRect.heightProperty().unbind();
        selectRect.visibleProperty().unbind();
    }

    private boolean passesFilter(MouseEvent event) {
        if (mouseFilter != null) {
            MouseEvent cloned = (MouseEvent) event.clone();
            mouseFilter.handle(cloned);
            if (cloned.isConsumed())
                return false;
        }

        return true;
    }

    private void onMousePressed(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();

        Rectangle2D plotArea = chartInfo.getPlotArea();
        if (!plotArea.contains(new Point2D(mouseEvent.getX(), mouseEvent.getY()))) return;
        DefaultChartInputContext context = new DefaultChartInputContext(chartInfo, x, y);
        zoomMode = axisConstraintStrategy.getConstraint(context);

        if (zoomMode == AxisConstraint.Both) {
            selectRect.setTranslateX(x);
            selectRect.setTranslateY(y);
            rectX.set(x);
            rectY.set(y);

        } else if (zoomMode == AxisConstraint.Horizontal) {
            selectRect.setTranslateX(x);
            selectRect.setTranslateY(plotArea.getMinY());
            rectX.set(x);
            rectY.set(plotArea.getMaxY());

        } else if (zoomMode == AxisConstraint.Vertical) {
            selectRect.setTranslateX(plotArea.getMinX());
            selectRect.setTranslateY(y);
            rectX.set(plotArea.getMaxX());
            rectY.set(y);
        }
    }

    private void onDragStart() {
        //Don't actually start the selecting process until it's officially a drag
        //But, we saved the original coordinates from where we started.
        if (zoomMode != AxisConstraint.None)
            selecting.set(true);
    }


    private void onMouseDragged(MouseEvent mouseEvent) {
        if (!selecting.get())
            return;

        Rectangle2D plotArea = chartInfo.getPlotArea();

        if (zoomMode == AxisConstraint.Both || zoomMode == AxisConstraint.Horizontal) {
            double x = mouseEvent.getX();
            //Clamp to the selection start
            x = Math.max(x, selectRect.getTranslateX());
            //Clamp to plot area
            x = Math.min(x, plotArea.getMaxX());
            rectX.set(x);
        }

        if (zoomMode == AxisConstraint.Both || zoomMode == AxisConstraint.Vertical) {
            double y = mouseEvent.getY();
            //Clamp to the selection start
            y = Math.max(y, selectRect.getTranslateY());
            //Clamp to plot area
            y = Math.min(y, plotArea.getMaxY());
            rectY.set(y);
        }
    }

    private void onMouseReleased() {
        if (!selecting.get())
            return;

        //Prevent a silly zoom... I'm still undecided about && vs ||
        if (selectRect.getWidth() == 0.0 ||
                selectRect.getHeight() == 0.0) {
            selecting.set(false);
            return;
        }

        Rectangle2D zoomWindow = chartInfo.getDataCoordinates(
                selectRect.getTranslateX(), selectRect.getTranslateY(),
                rectX.get(), rectY.get()
        );

        xAxis.setAutoRanging(false);
        yAxis.setAutoRanging(false);
        if (zoomAnimated.get()) {
            zoomAnimation.stop();
            zoomAnimation.getKeyFrames().setAll(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(xAxis.lowerBoundProperty(), xAxis.getLowerBound()),
                            new KeyValue(xAxis.upperBoundProperty(), xAxis.getUpperBound()),
                            new KeyValue(yAxis.lowerBoundProperty(), yAxis.getLowerBound()),
                            new KeyValue(yAxis.upperBoundProperty(), yAxis.getUpperBound())
                    ),
                    new KeyFrame(Duration.millis(zoomDurationMillis.get()),
                            new KeyValue(xAxis.lowerBoundProperty(), zoomWindow.getMinX()),
                            new KeyValue(xAxis.upperBoundProperty(), zoomWindow.getMaxX()),
                            new KeyValue(yAxis.lowerBoundProperty(), zoomWindow.getMinY()),
                            new KeyValue(yAxis.upperBoundProperty(), zoomWindow.getMaxY())
                    )
            );
            zoomAnimation.play();
        } else {
            zoomAnimation.stop();
            xAxis.setLowerBound(zoomWindow.getMinX());
            xAxis.setUpperBound(zoomWindow.getMaxX());
            yAxis.setLowerBound(zoomWindow.getMinY());
            yAxis.setUpperBound(zoomWindow.getMaxY());
        }

        selecting.set(false);
    }

    private static double getBalance(double val, double min, double max) {
        if (val <= min)
            return 0.0;
        else if (val >= max)
            return 1.0;

        return (val - min) / (max - min);
    }

    //TODO: сделать ограничения по зумированию с помощью колёсика мыши
    private class MouseWheelZoomHandler implements EventHandler<ScrollEvent> {
        private boolean ignoring = false;

        @Override
        public void handle(ScrollEvent event) {
            double eventX = event.getX();
            double eventY = event.getY();
            Rectangle2D plotArea = chartInfo.getPlotArea();
            if (!plotArea.contains(new Point2D(eventX, eventY))) return;
            EventType<? extends Event> eventType = event.getEventType();
            if (eventType == ScrollEvent.SCROLL_STARTED) {
                //mouse wheel events never send SCROLL_STARTED
                ignoring = true;
            } else if (eventType == ScrollEvent.SCROLL_FINISHED) {
                //end non-mouse wheel event
                ignoring = false;

            } else if (eventType == ScrollEvent.SCROLL &&
                    //If we are allowing mouse wheel zooming
                    mouseWheelZoomAllowed.get() &&
                    //If we aren't between SCROLL_STARTED and SCROLL_FINISHED
                    !ignoring &&
                    //inertia from non-wheel gestures might have touch count of 0
                    !event.isInertia() &&
                    //Only care about vertical wheel events
                    event.getDeltaY() != 0 &&
                    //mouse wheel always has touch count of 0
                    event.getTouchCount() == 0) {

                //Find out which axes to zoom based on the strategy
//                double eventX = event.getX();
//                double eventY = event.getY();
                DefaultChartInputContext context = new DefaultChartInputContext(chartInfo, eventX, eventY);
                AxisConstraint zoomMode = mouseWheelAxisConstraintStrategy.getConstraint(context);

                if (zoomMode == AxisConstraint.None)
                    return;

                //If we are are doing a zoom animation, stop it. Also of note is that we don't zoom the
                //mouse wheel zooming. Because the mouse wheel can "fly" and generate a lot of events,
                //animation doesn't work well. Plus, as the mouse wheel changes the view a small amount in
                //a predictable way, it "looks like" an animation when you roll it.
                //We might experiment with mouse wheel zoom animation in the future, though.
                zoomAnimation.stop();

                //At this point we are a mouse wheel event, based on everything I've read
                Point2D dataCoords = chartInfo.getDataCoordinates(eventX, eventY);

                //Determine the proportion of change to the lower and upper bounds based on how far the
                //cursor is along the axis.
                double xZoomBalance = getBalance(dataCoords.getX(),
                        xAxis.getLowerBound(), xAxis.getUpperBound());
                double yZoomBalance = getBalance(dataCoords.getY(),
                        yAxis.getLowerBound(), yAxis.getUpperBound());

                //Are we zooming in or out, based on the direction of the roll
                // если "-" - приближение, "+" - удаление
                byte direction = (byte)-Math.signum(event.getDeltaY());

//                System.out.println(direction);
//                System.out.println(direction > 0 ? "out" : "in");

                //TODO: Do we need to handle "continuous" scroll wheels that don't work based on ticks?
                //If so, the 0.2 needs to be modified
                /**
                *double zoomAmount = 0.2 * direction;
                 */
                if (zoomMode == AxisConstraint.Both || zoomMode == AxisConstraint.Horizontal) {
                 /**
                    double xZoomDelta = (xAxis.getUpperBound() - xAxis.getLowerBound()) * zoomAmount;
                    xAxis.setAutoRanging(false);
                    double newXLowerBound = xAxis.getLowerBound() - xZoomDelta * xZoomBalance;
                    double newXUpperBound = xAxis.getUpperBound() + xZoomDelta * (1 - xZoomBalance);
                  */

                 byte tempZoomLevel = (byte)(zoomLevel + direction);
                 if(tempZoomLevel >= 0 && tempZoomLevel <= 6){
                     double XTickUnit;
//                     double XTickUnit = 1 << tempZoomLevel;
                     double halfInterval;
                     double currentLowerBound = xAxis.getLowerBound();
                     double currentUpperBound = xAxis.getUpperBound();
                     double newLowerBound;
                     double newUpperBound;
                     if(direction < 0){
                         halfInterval = ((1 << zoomLevel) - (1 << (zoomLevel - 1))) * 60 / 2;
                         newLowerBound = currentLowerBound + halfInterval;
                         newUpperBound = currentUpperBound - halfInterval;
                     } else {
                         double interval = ((1 << (zoomLevel + 1)) - (1 << zoomLevel)) * 60;
//                       chartImpl.getDataSeries().getData().get(0) - при чистке истории может измениться
                         if(chartImpl.getPrevX() - (double)chartImpl.getDataSeries().getData().get(0).getXValue() < interval) return;
                         halfInterval =  interval / 2.0;
                         newLowerBound = currentLowerBound - halfInterval;
                         newUpperBound = currentUpperBound + halfInterval;
                     }
                     if(newLowerBound < AppConstants.TIME_MIN_LOWER_BOUND){
                         xAxis.setLowerBound(AppConstants.TIME_MIN_LOWER_BOUND);
                         newUpperBound = Math.abs(newLowerBound) + newUpperBound;
                         if(newUpperBound > AppConstants.TIME_MAX_UPPER_BOUND){
                             xAxis.setUpperBound(AppConstants.TIME_MAX_UPPER_BOUND);
                         } else {
                             xAxis.setUpperBound(newUpperBound);
                         }
//                         ((NumberAxis)xAxis).setTickUnit(XTickUnit);
                         XTickUnit = (xAxis.getUpperBound() - xAxis.getLowerBound()) / 60;
                         chartImpl.setXTick(XTickUnit);
                         ((NumberAxis)xAxis).setTickUnit(XTickUnit);
                         zoomLevel = tempZoomLevel;
                         return;
                     }
                     if (newUpperBound > AppConstants.TIME_MAX_UPPER_BOUND){
                         xAxis.setUpperBound(AppConstants.TIME_MAX_UPPER_BOUND);
                         newLowerBound = newUpperBound - AppConstants.TIME_MAX_UPPER_BOUND + newLowerBound;
                         if(newLowerBound < AppConstants.TIME_MIN_LOWER_BOUND){
                             xAxis.setLowerBound(AppConstants.TIME_MIN_LOWER_BOUND);
                         } else {
                             xAxis.setLowerBound(newLowerBound);
                         }
//                         ((NumberAxis)xAxis).setTickUnit(XTickUnit);
                         XTickUnit = (xAxis.getUpperBound() - xAxis.getLowerBound()) / 60;
                         chartImpl.setXTick(XTickUnit);
                         ((NumberAxis)xAxis).setTickUnit(XTickUnit);
                         zoomLevel = tempZoomLevel;
                         return;
                     }

                     xAxis.setLowerBound(newLowerBound);
                     xAxis.setUpperBound(newUpperBound);
//                     ((NumberAxis)xAxis).setTickUnit(XTickUnit);
                     XTickUnit = (xAxis.getUpperBound() - xAxis.getLowerBound()) / 60;
                     chartImpl.setXTick(XTickUnit);
                     ((NumberAxis)xAxis).setTickUnit(XTickUnit);
                     zoomLevel = tempZoomLevel;
//

//                     double halfInterval = Math.abs(XTickUnit * (1 - (1 << direction)))/2.0*60;
//                     double newXLowerBound = xAxis.getLowerBound() - direction*halfInterval;
//                     double newXUpperBound = xAxis.getUpperBound() + direction*halfInterval;
//                     if(newXLowerBound < AppConstants.TIME_MIN_LOWER_BOUND){
//                         xAxis.setLowerBound(AppConstants.TIME_MIN_LOWER_BOUND);
//                         // ??
//                         xAxis.setUpperBound(newXUpperBound + direction*halfInterval);
//
//                     } else if(newXUpperBound > AppConstants.TIME_MAX_UPPER_BOUND){
//                         // ??
//                         xAxis.setLowerBound(newXLowerBound - direction*halfInterval);
//                         xAxis.setUpperBound(AppConstants.TIME_MAX_UPPER_BOUND);
//                     } else {
//                         xAxis.setLowerBound(newXLowerBound);
//                         xAxis.setUpperBound(newXUpperBound);
//                     }
//                     ((NumberAxis)xAxis).setTickUnit(XTickUnit);

                 }
//                    if (newXLowerBound >= 0){
//                        xAxis.setLowerBound(newXLowerBound);
//                        xAxis.setUpperBound(newXUpperBound);
//                    }
                }

//                отключаем зумирование колёсиком по вертикальной оси
//                if (zoomMode == AxisConstraint.Both || zoomMode == AxisConstraint.Vertical) {
//                    double yZoomDelta = (yAxis.getUpperBound() - yAxis.getLowerBound()) * zoomAmount;
//                    yAxis.setAutoRanging(false);
//                    double newYLowerBound = yAxis.getLowerBound() - yZoomDelta * yZoomBalance;
//                    double newYUpperBound = yAxis.getUpperBound() + yZoomDelta * (1 - yZoomBalance);
//                    if(newYLowerBound >= 0){
//                        yAxis.setLowerBound(newYLowerBound);
//                        yAxis.setUpperBound(newYUpperBound);
//                    }
//
//                }
            }
        }
    }

    public XYChartInfo getChartInfo() {
        return chartInfo;
    }
}
