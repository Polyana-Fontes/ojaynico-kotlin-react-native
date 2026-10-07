@file:JsModule("react-native")
@file:Suppress("unused")

package ojaynico.kotlin.react.native.api

import js.promise.Promise
import react.Context

external interface ScaledSize {
    val width: Number
    val height: Number
    val scale: Number
    val fontScale: Number
}

external interface EmitterSubscription {
    fun remove()
}

external object I18nManager {
    val isRTL: Boolean
    val doLeftAndRightSwapInRTL: Boolean
    fun getConstants(): dynamic
    fun allowRTL(shouldAllow: Boolean)
    fun forceRTL(shouldForce: Boolean)
    fun swapLeftAndRightInRTL(flipStyles: Boolean)
}

external object LogBox {
    fun install()
    fun uninstall()
    fun isInstalled(): Boolean
    fun ignoreLogs(patterns: Array<dynamic>)
    fun ignoreAllLogs(value: Boolean = definedExternally)
    fun clearAllLogs()
    fun addLog(log: dynamic)
    fun addConsoleLog(level: String, vararg args: dynamic)
    fun addException(error: dynamic)
}

external object Clipboard {
    fun getString(): Promise<JsString>
    fun setString(content: String)
}

external object DevMenu {
    fun show()
}

external object DeviceInfo {
    val Dimensions: dynamic
    fun getConstants(): dynamic
}

external class ReactNativeVersion {
    companion object {
        val major: Number
        val minor: Number
        val patch: Number
        val prerelease: String?
        fun getVersionString(): String
    }
}

external val RootTagContext: Context<JsNumber>

external object UTFSequence {
    val BOM: String
    val BULLET: String
    val BULLET_SP: String
    val MDASH: String
    val MDASH_SP: String
    val MIDDOT: String
    val MIDDOT_KATAKANA: String
    val MIDDOT_SP: String
    val NBSP: String
    val NDASH: String
    val NDASH_SP: String
    val NEWLINE: String
    val PIZZA: String
    val TRIANGLE_LEFT: String
    val TRIANGLE_RIGHT: String
}

external class NativeEventEmitter(nativeModule: dynamic = definedExternally) {
    fun addListener(eventType: String, listener: (dynamic) -> Unit, context: dynamic = definedExternally): EmitterSubscription
    fun removeAllListeners(eventType: String = definedExternally)
    fun listenerCount(eventType: String): Number
}

external object DeviceEventEmitter {
    fun addListener(eventType: String, listener: (dynamic) -> Unit): EmitterSubscription
    fun emit(eventType: String, vararg params: dynamic)
    fun removeAllListeners(eventType: String = definedExternally)
    fun listenerCount(eventType: String): Number
}

external object NativeAppEventEmitter {
    fun addListener(eventType: String, listener: (dynamic) -> Unit): EmitterSubscription
    fun emit(eventType: String, vararg params: dynamic)
    fun removeAllListeners(eventType: String = definedExternally)
}

external val NativeModules: dynamic

external object TurboModuleRegistry {
    fun get(name: String): dynamic
    fun getEnforcing(name: String): dynamic
}

external object UIManager {
    fun measure(node: dynamic, callback: (x: Number, y: Number, width: Number, height: Number, pageX: Number, pageY: Number) -> Unit)
    fun measureInWindow(node: dynamic, callback: (x: Number, y: Number, width: Number, height: Number) -> Unit)
    fun measureLayout(node: dynamic, relativeToNativeNode: dynamic, onFail: () -> Unit, onSuccess: (left: Number, top: Number, width: Number, height: Number) -> Unit)
    fun setLayoutAnimationEnabledExperimental(value: Boolean)
    fun getViewManagerConfig(name: String): dynamic
    fun getConstantsForViewManager(name: String): dynamic
    fun dispatchViewManagerCommand(node: dynamic, command: dynamic, args: dynamic)
    fun hasViewManagerConfig(name: String): Boolean
}

external val NativeComponentRegistry: dynamic

external val NativeDialogManagerAndroid: dynamic

external val Networking: dynamic

external object PushNotificationIOS {
    fun requestPermissions(permissions: dynamic = definedExternally): Promise<JsAny>
    fun abandonPermissions()
    fun checkPermissions(callback: (dynamic) -> Unit)
    fun addEventListener(type: String, handler: (dynamic) -> Unit)
    fun removeEventListener(type: String)
    fun getInitialNotification(): Promise<JsAny?>
    fun getApplicationIconBadgeNumber(callback: (Number) -> Unit)
    fun setApplicationIconBadgeNumber(number: Number)
    fun presentLocalNotification(details: dynamic)
    fun scheduleLocalNotification(details: dynamic)
    fun cancelAllLocalNotifications()
    fun removeAllDeliveredNotifications()
}

external fun processColor(color: dynamic): dynamic

external fun findNodeHandle(componentOrHandle: dynamic): JsNumber?

external fun codegenNativeComponent(componentName: String, options: dynamic = definedExternally): dynamic

external fun codegenNativeCommands(options: dynamic): dynamic

external fun requireNativeComponent(viewName: String): dynamic

external fun registerCallableModule(name: String, module: dynamic)

external fun unstable_batchedUpdates(fn: () -> Unit)

external fun <T> unstable_batchedUpdates(fn: (bookkeeping: T) -> Unit, bookkeeping: T)

external fun useAnimatedValue(initialValue: Number, config: dynamic = definedExternally): Animated.Value

external fun useAnimatedValueXY(initialValue: dynamic = definedExternally, config: dynamic = definedExternally): Animated.ValueXY

external fun useAnimatedColor(initialValue: dynamic, config: dynamic = definedExternally): Animated.Color

external fun useColorScheme(): String?

external fun useWindowDimensions(): ScaledSize

external fun usePressability(config: dynamic): dynamic

external val EventEmitter: dynamic

external val unstable_TextAncestorContext: dynamic

external val VirtualViewMode: dynamic

external val unstable_VirtualArray: dynamic

external fun unstable_createVirtualCollectionView(options: dynamic): dynamic

external val unstable_VirtualColumnGenerator: dynamic

external fun unstable_getScrollParent(node: dynamic): dynamic

external val unstable_DEFAULT_INITIAL_NUM_TO_RENDER: Int
