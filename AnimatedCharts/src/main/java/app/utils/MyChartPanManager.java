package app.utils;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.chart.ValueAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import org.gillius.jfxutils.EventHandlerManager;
import org.gillius.jfxutils.chart.*;
import app.view.charts.ChartInterface;

public class MyChartPanManager {

    public static final EventHandler<MouseEvent> DEFAULT_FILTER = ChartZoomManager.DEFAULT_FILTER;

    private final EventHandlerManager handlerManager;

    private final ValueAxis<?> xAxis;
    private final ValueAxis<?> yAxis;
    private final XYChartInfo chartInfo;
    private final ChartInterface chartImpl;

    private AxisConstraint panMode = AxisConstraint.None;
    private AxisConstraintStrategy axisConstraintStrategy = AxisConstraintStrategies.getDefault();

    private EventHandler<? super MouseEvent> mouseFilter = DEFAULT_FILTER;

    private boolean dragging = false;

//    private boolean wasXAnimated;
//    private boolean wasYAnimated;

    // флаг, позволяющий перетаскивать график к более ранним значениям
    private boolean isPanning = false;

    private double lastX;
    private double lastY;

    public MyChartPanManager(ChartInterface chartImpl) {
        this.chartImpl = chartImpl;
        XYChart<?, ?> chart = chartImpl.getChart();
        handlerManager = new EventHandlerManager(chart);
        xAxis = (ValueAxis<?>) chart.getXAxis();
        yAxis = (ValueAxis<?>) chart.getYAxis();
        chartInfo = new XYChartInfo(chart, chart);

        handlerManager.addEventHandler(false, MouseEvent.DRAG_DETECTED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (passesFilter(mouseEvent))
                    startDrag(mouseEvent);
            }
        });

        handlerManager.addEventHandler(false, MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                drag(mouseEvent);
            }
        });

        handlerManager.addEventHandler(false, MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                release();
            }
        });
    }

    /**
     * Returns the current strategy in use.
     *
     * @see #setAxisConstraintStrategy(AxisConstraintStrategy)
     */
    public AxisConstraintStrategy getAxisConstraintStrategy() {
        return axisConstraintStrategy;
    }

    /**
     * Sets the {@link AxisConstraintStrategy} to use, which determines which axis is allowed for panning. The default
     * implementation is {@link AxisConstraintStrategies#getDefault()}.
     *
     * @see AxisConstraintStrategies
     */
    public void setAxisConstraintStrategy(AxisConstraintStrategy axisConstraintStrategy) {
        this.axisConstraintStrategy = axisConstraintStrategy;
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
     * Sets the mouse filter for starting the pan action. If the filter consumes the event with
     * {@link Event#consume()}, then the event is ignored. If the filter is null, all events are
     * passed through. The default filter is {@link #DEFAULT_FILTER}.
     */
    public void setMouseFilter(EventHandler<? super MouseEvent> mouseFilter) {
        this.mouseFilter = mouseFilter;
    }

    public void start() {
        handlerManager.addAllHandlers();
    }

    public void stop() {
        handlerManager.removeAllHandlers();
        release();
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

    private void startDrag(MouseEvent event) {
        DefaultChartInputContext context = new DefaultChartInputContext(chartInfo, event.getX(), event.getY());
        panMode = axisConstraintStrategy.getConstraint(context);
        if (!chartInfo.getPlotArea().contains(new Point2D(event.getX(), event.getY()))) {
            dragging = false;
            return;
        }
        if (panMode != AxisConstraint.None) {
            lastX = event.getX();
            lastY = event.getY();
//          сохраняем свойство анимации по осям перед тем как её выключить
//            wasXAnimated = xAxis.getAnimated();
//            wasYAnimated = yAxis.getAnimated();

            xAxis.setAnimated(false);
            xAxis.setAutoRanging(false);
            yAxis.setAnimated(false);
            yAxis.setAutoRanging(false);

            dragging = true;
        }
    }


    private void drag(MouseEvent event) {
        if (!chartInfo.getPlotArea().contains(new Point2D(event.getX(), event.getY()))) {
//            dragging = false;
            return;
        }
        if (!dragging)
            return;


        if (panMode == AxisConstraint.Both || panMode == AxisConstraint.Horizontal) {
            double dX = (event.getX() - lastX) / -xAxis.getScale();
            lastX = event.getX();
            xAxis.setAutoRanging(false);
            double xLowerBound = xAxis.getLowerBound();
            double xUpperBound = xAxis.getUpperBound();

            if (xLowerBound + dX >= 0 && (xUpperBound + dX <= chartImpl.getPrevX() + 6 * chartImpl.getXTick() || xUpperBound > chartImpl.getPrevX() + 6 * chartImpl.getXTick() && dX < 0)) {
                System.out.println("1");
//                if(-dX >= chartImpl.getXTick()/10) {
//                    System.out.println("3");
//                    isPanning = true;
//                    xAxis.setLowerBound(xLowerBound + dX);
//                    xAxis.setUpperBound(xUpperBound + dX);
//                    return;
//                }
                if (xUpperBound + dX >= chartImpl.getPrevX() + 3 * chartImpl.getXTick()) {

                    isPanning = false;
                    double upper = chartImpl.getPrevX() + 6 * chartImpl.getXTick();
                    double interval = ((1 << chartImpl.getZoomLevel()) - (1 << (chartImpl.getZoomLevel() - 1))) * 60;
                    xAxis.setUpperBound(upper);
                    xAxis.setLowerBound(upper - interval);

//                } else if (xUpperBound + dX < chartImpl.getPrevX() + 3 * chartImpl.getXTick()) {
                }  else {
                    isPanning = true;
                    xAxis.setLowerBound(xLowerBound + dX);
                    xAxis.setUpperBound(xUpperBound + dX);
                }
//                } else{
//                    xAxis.setLowerBound(xLowerBound + dX);
//                    xAxis.setUpperBound(xUpperBound + dX);
//                }
            }
        }

        if (panMode == AxisConstraint.Both || panMode == AxisConstraint.Vertical) {

            double dY = (event.getY() - lastY) / -yAxis.getScale();

            lastY = event.getY();
            yAxis.setAutoRanging(false);

            if (yAxis.getLowerBound() + dY >= 0) {
                yAxis.setLowerBound(yAxis.getLowerBound() + dY);
                yAxis.setUpperBound(yAxis.getUpperBound() + dY);
            }
        }
    }


    private void release() {
        if (!dragging)
            return;

        dragging = false;

//        xAxis.setAnimated(wasXAnimated);
//        yAxis.setAnimated(wasYAnimated);
    }

    public boolean isPanning() {
        return isPanning;
    }
}
