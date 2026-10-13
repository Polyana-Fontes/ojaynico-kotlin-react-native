@file:JsModule("react-native-safe-area-context")
@file:Suppress("unused")

package ojaynico.kotlin.react.native.safearea

import react.FC
import react.PropsWithChildren

external interface EdgeInsets {
    val top: Double
    val right: Double
    val bottom: Double
    val left: Double
}

external interface Rect {
    val x: Double
    val y: Double
    val width: Double
    val height: Double
}

external interface Metrics {
    val insets: EdgeInsets
    val frame: Rect
}

// SafeAreaProvider Component
external interface SafeAreaProviderProps : PropsWithChildren {
    var initialMetrics: Metrics?
    var style: dynamic
}

external val SafeAreaProvider: FC<SafeAreaProviderProps>

// SafeAreaView Component
external interface SafeAreaViewProps : PropsWithChildren {
    var edges: dynamic
    var mode: String?
    var style: dynamic
}

external val SafeAreaView: FC<SafeAreaViewProps>

external val initialWindowMetrics: Metrics?

external fun useSafeAreaInsets(): EdgeInsets

external fun useSafeAreaFrame(): Rect
