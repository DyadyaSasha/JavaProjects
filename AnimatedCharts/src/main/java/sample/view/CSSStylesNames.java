package sample.view;

public enum CSSStylesNames {

    ALL_CHARTS_BOX("allChartsBox"),
    CROSS_VALUE_MARKER("crossMarker"),
    STOCK_CHART("stockChart"),
    ZOOM_RECTANGLE("zoomRectangle"),
    CROSS_LABELS("crossLabels"),
    STOCK_CHART_PANE("stockChartPane"),
    AREA1("area1");

    private String styleName;

    CSSStylesNames(String styleName) {
        this.styleName = styleName;
    }

    public String getStyleName() {
        return styleName;
    }
}
