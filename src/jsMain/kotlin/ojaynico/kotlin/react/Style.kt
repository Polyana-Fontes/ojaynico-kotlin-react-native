@file:Suppress("unused")

package ojaynico.kotlin.react

import kotlinx.js.JsPlainObject

// Style objects are plain JavaScript objects, as React Native and react-native-web expect.
// @JsPlainObject generates the factories, so `ViewStyle(flex = 1, padding = 8)` sets only the given keys.

/**
 * Flexbox and box model properties shared by every style.
 */
@JsPlainObject
external interface LayoutStyle {
    val alignContent: dynamic
    val alignItems: dynamic
    val alignSelf: dynamic
    val aspectRatio: Number?
    val backgroundColor: String?
    val borderBottomWidth: Number?
    val borderColor: dynamic
    val borderEndWidth: dynamic
    val borderLeftWidth: Number?
    val borderRightWidth: Number?
    val borderStartWidth: dynamic
    val borderTopWidth: Number?
    val borderWidth: Number?
    val bottom: dynamic
    val direction: dynamic
    val display: dynamic
    val end: dynamic
    val flex: Number?
    val flexBasis: dynamic
    val flexDirection: dynamic
    val flexGrow: Number?
    val flexShrink: Number?
    val flexWrap: dynamic
    val height: dynamic
    val justifyContent: dynamic
    val left: dynamic
    val margin: dynamic
    val marginBottom: dynamic
    val marginEnd: dynamic
    val marginHorizontal: dynamic
    val marginLeft: dynamic
    val marginRight: dynamic
    val marginStart: dynamic
    val marginTop: dynamic
    val marginVertical: dynamic
    val maxHeight: dynamic
    val maxWidth: dynamic
    val minHeight: dynamic
    val minWidth: dynamic
    val overflow: dynamic
    val padding: dynamic
    val paddingBottom: dynamic
    val paddingEnd: dynamic
    val paddingHorizontal: dynamic
    val paddingLeft: dynamic
    val paddingRight: dynamic
    val paddingStart: dynamic
    val paddingTop: dynamic
    val paddingVertical: dynamic
    val position: dynamic
    val right: dynamic
    val start: dynamic
    val top: dynamic
    val width: dynamic
    val zIndex: Number?
}

/**
 * Shadow properties for views and images.
 */
@JsPlainObject
external interface ShadowStyle : LayoutStyle {
    val shadowColor: String?
    val shadowOffset: dynamic
    val shadowOpacity: Number?
    val shadowRadius: Number?
}

/**
 * Style for `View` and other container components.
 */
@JsPlainObject
external interface ViewStyle : ShadowStyle {
    val backfaceVisibility: String?
    val borderBottomColor: String?
    val borderBottomEndRadius: Number?
    val borderBottomLeftRadius: Number?
    val borderBottomRightRadius: Number?
    val borderBottomStartRadius: Number?
    val borderEndColor: String?
    val borderLeftColor: String?
    val borderRadius: Number?
    val borderRightColor: String?
    val borderStartColor: String?
    val borderStyle: String?
    val borderTopColor: String?
    val borderTopEndRadius: Number?
    val borderTopLeftRadius: Number?
    val borderTopRightRadius: Number?
    val borderTopStartRadius: Number?
    val boxShadow: dynamic
    val columnGap: Number?
    val elevation: Number?
    val gap: Number?
    val opacity: Number?
    val rowGap: Number?
    val transform: dynamic
}

/**
 * Style for `Text` and `TextInput`. React Native text accepts every view style as well.
 */
@JsPlainObject
external interface TextStyle : ViewStyle {
    val color: String?
    val fontFamily: String?
    val fontSize: Number?
    val fontStyle: String?
    val fontVariant: dynamic
    val fontWeight: String?
    val includeFontPadding: Boolean?
    val letterSpacing: Number?
    val lineHeight: Number?
    val textAlign: String?
    val textAlignVertical: String?
    val textDecorationColor: String?
    val textDecorationLine: String?
    val textDecorationStyle: String?
    val textShadowColor: String?
    val textShadowOffset: dynamic
    val textShadowRadius: Number?
    val textTransform: String?
    val writingDirection: String?
}

/**
 * Style for `Image` and `ImageBackground`.
 */
@JsPlainObject
external interface ImageStyle : ShadowStyle {
    val backfaceVisibility: String?
    val borderBottomLeftRadius: Number?
    val borderBottomRightRadius: Number?
    val borderRadius: Number?
    val borderTopLeftRadius: Number?
    val borderTopRightRadius: Number?
    val opacity: Number?
    val overlayColor: String?
    val resizeMode: String?
    val tintColor: String?
}
