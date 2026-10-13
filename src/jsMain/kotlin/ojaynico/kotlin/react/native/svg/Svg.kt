@file:JsModule("react-native-svg")
@file:Suppress("unused")

package ojaynico.kotlin.react.native.svg

import react.FC
import react.Props
import react.PropsWithChildren

// Presentation attributes shared by every react-native-svg shape.
external interface SvgPresentationProps : Props {
    var color: String?
    var fill: String?
    var fillOpacity: Number?
    var fillRule: String?
    var opacity: Number?
    var stroke: String?
    var strokeWidth: Number?
    var strokeOpacity: Number?
    var strokeLinecap: String?
    var strokeLinejoin: String?
    var strokeDasharray: dynamic
    var strokeDashoffset: Number?
    var strokeMiterlimit: Number?
    var transform: dynamic
    var testID: String?
}

// Svg Component
external interface SvgProps : SvgPresentationProps, PropsWithChildren {
    var width: dynamic
    var height: dynamic
    var viewBox: String?
    var preserveAspectRatio: String?
    var style: dynamic
    var accessible: Boolean?
    var accessibilityLabel: String?
    var importantForAccessibility: String?
}

@JsName("default")
external val Svg: FC<SvgProps>

// G Component
external interface GProps : SvgPresentationProps, PropsWithChildren

external val G: FC<GProps>

// Path Component
external interface PathProps : SvgPresentationProps {
    var d: String
}

external val Path: FC<PathProps>

// Line Component
external interface LineProps : SvgPresentationProps {
    var x1: Number
    var y1: Number
    var x2: Number
    var y2: Number
}

external val Line: FC<LineProps>

// Circle Component
external interface CircleProps : SvgPresentationProps {
    var cx: Number
    var cy: Number
    var r: Number
}

external val Circle: FC<CircleProps>

// Ellipse Component
external interface EllipseProps : SvgPresentationProps {
    var cx: Number
    var cy: Number
    var rx: Number
    var ry: Number
}

external val Ellipse: FC<EllipseProps>

// Rect Component
external interface RectProps : SvgPresentationProps {
    var x: Number
    var y: Number
    var width: dynamic
    var height: dynamic
    var rx: Number?
    var ry: Number?
}

external val Rect: FC<RectProps>

// Polyline Component
external interface PolylineProps : SvgPresentationProps {
    var points: String
}

external val Polyline: FC<PolylineProps>

// Polygon Component
external interface PolygonProps : SvgPresentationProps {
    var points: String
}

external val Polygon: FC<PolygonProps>

// Defs Component
external val Defs: FC<PropsWithChildren>

// LinearGradient Component
external interface LinearGradientProps : PropsWithChildren {
    var id: String
    var x1: dynamic
    var y1: dynamic
    var x2: dynamic
    var y2: dynamic
    var gradientUnits: String?
    var gradientTransform: dynamic
}

external val LinearGradient: FC<LinearGradientProps>

// Stop Component
external interface StopProps : Props {
    var offset: dynamic
    var stopColor: String
    var stopOpacity: Number?
}

external val Stop: FC<StopProps>
