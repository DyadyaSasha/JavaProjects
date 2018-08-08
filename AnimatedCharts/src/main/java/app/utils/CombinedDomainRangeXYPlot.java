package app.utils;

import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.chart.event.PlotChangeListener;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.util.Args;
import org.jfree.chart.util.ShadowGenerator;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class CombinedDomainRangeXYPlot extends XYPlot
        implements PlotChangeListener {


    //  список горизонтальных графиков
    private List<XYPlot> horizontalSubPlots;
    //  список вертикальных графиков
    private List<XYPlot> verticalSubPlots;

    private double gap = 0.5;
    //  пространства для графиков
    private Rectangle2D[] subplotsAreas;


    public CombinedDomainRangeXYPlot() {
        this(new NumberAxis(), new NumberAxis());
    }

    public CombinedDomainRangeXYPlot(ValueAxis domainAxis, ValueAxis rangeAxis) {
        super(null,
                domainAxis,
                rangeAxis,
                null);
        this.horizontalSubPlots = new ArrayList<>();
        this.verticalSubPlots = new ArrayList<>();
    }

    @Override
    public String getPlotType(){ return "Combined_Domain_Range_XYPlot";}

    public double getGap() {
        return gap;
    }

    public void setGap(double gep){
        this.gap = gap;
        fireChangeEvent();
    }

    @Override
    public boolean isRangePannable(){
        for (XYPlot subplot : verticalSubPlots){
            if(subplot.isRangePannable()){
                return true;
            }
        }
        return false;
    }

    @Override
    public void setRangePannable(boolean pannable){
        for(XYPlot subplot : verticalSubPlots){
            subplot.setRangePannable(pannable);
        }
    }

    @Override
    public boolean isDomainPannable(){
        for (XYPlot subplot : horizontalSubPlots){
            if(subplot.isRangePannable()){
                return true;
            }
        }
        return false;
    }

    @Override
    public void setDomainPannable(boolean pannable){
        for(XYPlot subplot : horizontalSubPlots){
            subplot.setRangePannable(pannable);
        }
    }

    @Override
    public void setOrientation(PlotOrientation orientation){
        super.setOrientation(orientation);
        horizontalSubPlots.forEach(xyPlot -> xyPlot.setOrientation(orientation));
        verticalSubPlots.forEach(xyPlot -> xyPlot.setOrientation(orientation));
    }


    @Override
    public void setShadowGenerator(ShadowGenerator generator){
        setNotify(false);
        super.setShadowGenerator(generator);
        horizontalSubPlots.forEach(xyPlot -> xyPlot.setShadowGenerator(generator));
        verticalSubPlots.forEach(xyPlot -> xyPlot.setShadowGenerator(generator));
        setNotify(true);
    }

    public void add(XYPlot subplot, int weight, boolean isVertical){
        Args.nullNotPermitted(subplot, "subplot");
        if(weight <= 0){
            throw new IllegalArgumentException("Require weight >= 1.");
        }

        subplot.setParent(this);
        subplot.setWeight(weight);
        subplot.setInsets(RectangleInsets.ZERO_INSETS, false);
        if(isVertical){
            subplot.setDomainAxis(null);
            verticalSubPlots.add(subplot);
            configureDomainAxes();
        } else {
            subplot.setRangeAxis(null);
            horizontalSubPlots.add(subplot);
            configureRangeAxes();
        }
        fireChangeEvent();

    }

//    public List getVertical


    @Override
    public void plotChanged(PlotChangeEvent event) {
        notifyListeners(event);
    }


}
