/*
 GNU Lesser General Public License

 Copyright (c) 2017 Wimmer, Simon-Justus

 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 to permit persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

 */
package app.view.jfreechart;


import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.fx.interaction.ZoomHandlerFX;
import org.jfree.chart.plot.CombinedRangeXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.util.Args;
import org.jfree.chart.util.ExportUtils;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.List;

//import org.sjwimmer.tacharting.chart.utils.CalculationUtils;

/**
 * Customized ChartViewer with javafx CrosshairOverlay for this Region
 */
public class TaChartViewer extends Region {

    private TaChartCanvas canvas;

    private final Line xCrosshair;
    private final Line yCrosshair;
    private final Label xLabel;
    private final Label yLabel;
    private boolean inArrow;
    private ImageView arrow;

    /**
     * The zoom rectangle is used to display the zooming region when
     * doing a drag-zoom with the mouse.  Most of the time this rectangle
     * is not visible.
     */
    private Rectangle zoomRectangle;

    /**
     * The context menu for the org.sjwimmer.tacharting.chart viewer.
     */
    private ContextMenu contextMenu;


    /**
     * Creates a new viewer to display the supplied org.sjwimmer.tacharting.chart in JavaFX.
     *
     * @param chart the org.sjwimmer.tacharting.chart ({@code null} permitted).
     */
    public TaChartViewer(JFreeChart chart) {
        this(chart, true);
    }

    /**
     * Creates a new viewer instance.
     *
     * @param chart              the org.sjwimmer.tacharting.chart ({@code null} permitted).
     * @param contextMenuEnabled enable the context menu?
     */
    public TaChartViewer(JFreeChart chart, boolean contextMenuEnabled) {
        this.canvas = new TaChartCanvas(chart);
        this.canvas.setTooltipEnabled(true);
        this.canvas.addMouseHandler(new TaZoomHandlerFX("zoom", this));
        setFocusTraversable(true);

        getChildren().add(this.canvas);

        this.zoomRectangle = new Rectangle(0, 0, Color.LIGHTSEAGREEN.deriveColor(0, 1, 1, 0.5));
        this.zoomRectangle.setManaged(false);
        this.zoomRectangle.setVisible(false);
        getChildren().add(this.zoomRectangle);

//        this.contextMenu = createContextMenu();
//        setOnContextMenuRequested((ContextMenuEvent event) -> {
//            contextMenu.show(TaChartViewer.this.getScene().getWindow(),
//                    event.getScreenX(), event.getScreenY());
//        });
//
//        getContextMenu().setOnShowing(
//                e -> TaChartViewer.this.getCanvas().setTooltipEnabled(false));
//        getContextMenu().setOnHiding(
//                e -> TaChartViewer.this.getCanvas().setTooltipEnabled(true));

        this.xCrosshair = new Line(0, 0, this.getPrefWidth(), 0);
        this.yCrosshair = new Line(0, 0, 0, this.getPrefHeight());

        this.yCrosshair.setFill(Color.BLACK);
        this.xCrosshair.setFill(Color.BLACK);
        this.xCrosshair.setStrokeWidth(1);
        this.yCrosshair.setStrokeWidth(1);
        this.xCrosshair.setMouseTransparent(true);
        this.yCrosshair.setMouseTransparent(true);
        this.getChildren().add(xCrosshair);
        this.getChildren().add(yCrosshair);
        this.xLabel = new Label("");
        this.yLabel = new Label("");
        this.yLabel.setMouseTransparent(true);
        this.xLabel.setMouseTransparent(true);

//      картинка возврата на текущее состояние графика - перенести в TaCanvas
        Image image = new Image(getClass().getResourceAsStream("/static/return-arrow.png"));
        arrow = new ImageView(image);
        arrow.setOnMouseEntered(event -> {
            inArrow = true;
            setCrosshairVisible(false, event);
        });
        arrow.setOnMouseExited(event -> {
            inArrow = false;
            setCrosshairVisible(true, event);
        });

        arrow.setOnMouseClicked(event -> {

            List<XYPlot> subplots = ((CombinedRangeXYPlot) getChart().getPlot()).getSubplots();
            XYPlot stockPlot = subplots.get(0);
            NumberAxis domainAxis = (NumberAxis) stockPlot.getDomainAxis();
            XYSeries stockSeries = ((XYSeriesCollection)stockPlot.getDataset()).getSeries(0);

            double prevX = stockSeries.getMaxX();
            double xTickSize = domainAxis.getTickUnit().getSize();

            double upper = prevX + 6*xTickSize;
            double interval = ((1 << canvas.getZoomLevel()) - (1 << (canvas.getZoomLevel() - 1))) * 60;

            domainAxis.setLowerBound(upper - interval);
            domainAxis.setUpperBound(upper);

            canvas.setxPanningSpeed(0.9);
            canvas.setPanning(false);
            arrow.setVisible(false);
        });

        arrow.setVisible(false);

        this.getChildren().add(xLabel);
        this.getChildren().add(yLabel);
        this.getChildren().add(arrow);

        this.setOnMouseExited(event -> {
            setCrosshairVisible(false, event);
        });

        this.setOnMouseDragged(event -> {
            updateCrosshair(event);
        });

        /**Custom Mouse Listener for the CrosshairOverlay */
        this.setOnMouseMoved(e -> updateCrosshair(e));
    }

    private void updateCrosshair(MouseEvent e) {

        if(inArrow) return;

        final double x = e.getX();
        final double y = e.getY();

        // область общего графика, в который входят два графика
        Rectangle2D dataArea = getCanvas().getRenderingInfo().getPlotInfo().getDataArea();

        if (dataArea.contains(x, y)) {
//            if(x > dataArea.getMinX() && y > dataArea.getMinY() && x < dataArea.getMaxX() && y < dataArea.getMaxY()) {
//                ((Node) e.getSource()).setCursor(Cursor.CROSSHAIR);
            setCrosshairVisible(true, e);

            Rectangle2D subDataArea = getCanvas().getRenderingInfo().getPlotInfo().getSubplotInfo(0).getDataArea();
            if (subDataArea.getMaxX() < x) {
                xLabel.setVisible(false);
                xCrosshair.setVisible(false);
            }
//                CombinedDomainXYPlot combinedDomainXYPlot = (CombinedDomainXYPlot) getCanvas().getChart().getPlot();
//                XYPlot plot = (XYPlot) combinedDomainXYPlot.getSubplots().get(0);
            CombinedRangeXYPlot combinedRangeXYPlot = (CombinedRangeXYPlot) getCanvas().getChart().getPlot();
//              левый график
            XYPlot plot = (XYPlot) combinedRangeXYPlot.getSubplots().get(0);

            ValueAxis xAxis = plot.getDomainAxis();
//              местоположение главной оси
            RectangleEdge xAxisEdge = plot.getDomainAxisEdge();

            // x - вертикальная линия и надпись
            xCrosshair.setStartY(dataArea.getMinY());
            xCrosshair.setStartX(x);
            xCrosshair.setEndY(dataArea.getMaxY());
            xCrosshair.setEndX(x);
            xLabel.setLayoutX(x);
            xLabel.setLayoutY(dataArea.getMinY());
//                Rectangle2D subDataAreax = getCanvas().getRenderingInfo().getPlotInfo().getSubplotInfo(0).getDataArea();
            double value = xAxis.java2DToValue(e.getX(), subDataArea, xAxisEdge);
//                long itemLong = (long) (value);
//                Date itemDate = new Date(itemLong);
            xLabel.setText(String.format("%.2f", value));
//                xLabel.setText(String.valueOf(new SimpleDateFormat().format(itemDate)));


//                org.jfree.chart.axis.ValueAxis yAxis = plot.getRangeAxis();
            org.jfree.chart.axis.ValueAxis yAxis = combinedRangeXYPlot.getRangeAxis();
            RectangleEdge yAxisEdge = combinedRangeXYPlot.getRangeAxisEdge();
//                Rectangle2D subDataArea = getCanvas().getRenderingInfo().getPlotInfo().getSubplotInfo(0).getDataArea();
//                Rectangle2D subDataArea = getCanvas().getRenderingInfo().getPlotInfo().getDataArea();

            yCrosshair.setStartY(y);
            yCrosshair.setStartX(dataArea.getMinX());
            yCrosshair.setEndX(dataArea.getMaxX());
            yCrosshair.setEndY(y);
            yLabel.setLayoutY(y);
            yLabel.setLayoutX(dataArea.getMinX());
            //  String yValue = CalculationUtils.roundToString(yAxis.java2DToValue(y, subDataArea, yAxisEdge), 2);
            //yLabel.setText(yValue);
            double yValue = yAxis.java2DToValue(y, subDataArea, yAxisEdge);
            yLabel.setText(String.format("%.2f", yValue));
        } else {
            setCrosshairVisible(false, e);
//                ((Node) e.getSource()).setCursor(Cursor.DEFAULT);
        }
    }


    private void setCrosshairVisible(boolean visible, MouseEvent event) {
        xCrosshair.setVisible(visible);
        yCrosshair.setVisible(visible);
        xLabel.setVisible(visible);
        yLabel.setVisible(visible);

        if (visible) {
            ((Node) event.getSource()).setCursor(Cursor.CROSSHAIR);
        } else {
            ((Node) event.getSource()).setCursor(Cursor.DEFAULT);
        }
    }

    /**
     * Returns the org.sjwimmer.tacharting.chart that is being displayed by this viewer.
     *
     * @return The org.sjwimmer.tacharting.chart (possibly {@code null}).
     */
    public JFreeChart getChart() {
        return this.canvas.getChart();
    }

    /**
     * Sets the org.sjwimmer.tacharting.chart to be displayed by this viewer.
     *
     * @param chart the org.sjwimmer.tacharting.chart ({@code null} not permitted).
     */
    public void setChart(JFreeChart chart) {
        Args.nullNotPermitted(chart, "Stock chart");
        this.canvas.setChart(chart);
    }

    /**
     * Returns the {@link TaChartCanvas} embedded in this component.
     *
     * @return The {@code ChartCanvas} (never {@code null}).
     * @since 1.0.20
     */
    public TaChartCanvas getCanvas() {
        return this.canvas;
    }

    /**
     * Returns the context menu for this component.
     *
     * @return The context menu for this component.
     */
    public ContextMenu getContextMenu() {
        return this.contextMenu;
    }

    /**
     * Returns the rendering info from the most recent drawing of the org.sjwimmer.tacharting.chart.
     *
     * @return The rendering info (possibly {@code null}).
     * @since 1.0.19
     */
    public ChartRenderingInfo getRenderingInfo() {
        return getCanvas().getRenderingInfo();
    }


    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        this.canvas.setLayoutX(getLayoutX());
        this.canvas.setLayoutY(getLayoutY());
        this.canvas.setWidth(getWidth());
        this.canvas.setHeight(getHeight());
    }

    /**
     * Creates the context menu.
     *
     * @return The context menu.
     */
    private ContextMenu createContextMenu() {
        final ContextMenu menu = new ContextMenu();
        menu.setAutoHide(true);
        Menu export = new Menu("Export As");

        MenuItem pngItem = new MenuItem("PNG...");
        pngItem.setOnAction(e -> handleExportToPNG());
        export.getItems().add(pngItem);

        MenuItem jpegItem = new MenuItem("JPEG...");
        jpegItem.setOnAction(e -> handleExportToJPEG());
        export.getItems().add(jpegItem);

        if (ExportUtils.isOrsonPDFAvailable()) {
            MenuItem pdfItem = new MenuItem("PDF...");
            pdfItem.setOnAction(e -> handleExportToPDF());
            export.getItems().add(pdfItem);
        }
        if (ExportUtils.isJFreeSVGAvailable()) {
            MenuItem svgItem = new MenuItem("SVG...");
            svgItem.setOnAction(e -> handleExportToSVG());
            export.getItems().add(svgItem);
        }
        menu.getItems().add(export);
        return menu;
    }

    /**
     * A handler for the export to PDF option in the context menu.
     */
    private void handleExportToPDF() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Export to PDF");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(
                "Portable Document Format (PDF)", "pdf");
        chooser.getExtensionFilters().add(filter);
        File file = chooser.showSaveDialog(getScene().getWindow());
        if (file != null) {
            ExportUtils.writeAsPDF(this.canvas.getChart(), (int) getWidth(),
                    (int) getHeight(), file);
        }
    }

    /**
     * A handler for the export to SVG option in the context menu.
     */
    private void handleExportToSVG() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Export to SVG");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(
                "Scalable Vector Graphics (SVG)", "svg");
        chooser.getExtensionFilters().add(filter);
        File file = chooser.showSaveDialog(getScene().getWindow());
        if (file != null) {
            ExportUtils.writeAsSVG(this.canvas.getChart(), (int) getWidth(),
                    (int) getHeight(), file);
        }
    }

    /**
     * A handler for the export to PNG option in the context menu.
     */
    private void handleExportToPNG() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Export to PNG");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(
                "Portable Network Graphics (PNG)", "png");
        chooser.getExtensionFilters().add(filter);
        File file = chooser.showSaveDialog(getScene().getWindow());
        if (file != null) {
            try {
                ExportUtils.writeAsPNG(this.canvas.getChart(), (int) getWidth(),
                        (int) getHeight(), file);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * A handler for the export to JPEG option in the context menu.
     */
    private void handleExportToJPEG() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Export to JPEG");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("JPEG", "jpg");
        chooser.getExtensionFilters().add(filter);
        File file = chooser.showSaveDialog(getScene().getWindow());
        if (file != null) {
            try {
                ExportUtils.writeAsJPEG(this.canvas.getChart(), (int) getWidth(),
                        (int) getHeight(), file);
            } catch (IOException ex) {
                // FIXME: show a dialog with the error
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * Sets the size and location of the zoom rectangle and makes it visible
     * if it wasn't already visible..  This method is provided for the use of
     * the {@link ZoomHandlerFX} class, you won't normally need to call it
     * directly.
     *
     * @param x the x-location.
     * @param y the y-location.
     * @param w the width.
     * @param h the height.
     */
    public void showZoomRectangle(double x, double y, double w, double h) {
        this.zoomRectangle.setX(x);
        this.zoomRectangle.setY(y);
        this.zoomRectangle.setWidth(w);
        this.zoomRectangle.setHeight(h);
        this.zoomRectangle.setVisible(true);
    }

    /**
     * Hides the zoom rectangle.  This method is provided for the use of the
     * {@link ZoomHandlerFX} class, you won't normally need to call it directly.
     */
    public void hideZoomRectangle() {
        this.zoomRectangle.setVisible(false);
    }

    public ImageView getArrow() {
        return arrow;
    }
}

