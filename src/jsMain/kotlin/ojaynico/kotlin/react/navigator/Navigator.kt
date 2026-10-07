package ojaynico.kotlin.react.navigator

import js.objects.Object
import js.reflect.unsafeCast
import ojaynico.kotlin.react.json
import ojaynico.kotlin.react.native.api.*
import ojaynico.kotlin.react.native.component.StyleSheet
import ojaynico.kotlin.react.native.component.View
import react.*
import web.events.EventHandler
import web.history.history
import web.window.window
import kotlin.js.Json

external interface RouteProps : Props {
    var name: String
    var component: dynamic
    var pathVariables: String
}

val Route = FC<RouteProps> { }

fun buildSceneConfig(children: Array<Json>, sideMenu: FC<*>): Json {
    val config = json { }

    children.forEach { child ->
        config[child.asDynamic().props.name] = json {
            key = child.asDynamic().props.name
            component = child.asDynamic().props.component
            pathVariables = child.asDynamic().props.pathVariables
        }
    }


    if (sideMenu.asDynamic() != undefined) {
        config["krnMenu"] = json {
            key = "krnMenu"
            component = sideMenu
        }
    }

    return config.unsafeCast<Json>()
}

external interface NavigatorFunctions {
    var pop: () -> Unit
    val push: (routeName: String, props: dynamic) -> Unit
    var showMenu: () -> Unit
}

external interface NavigationProps : Props {
    var navigator: NavigatorFunctions
}

val screenWidth = Dimensions.get("window").width

/*external interface NavigatorState : State {
    var initialSceneName: String
    var stack: List<Json>
    var sceneConfig: Json
    var showSideMenu: Boolean
    var touchX: dynamic
    var browserHistory: List<String>
}*/

external interface NavigatorProps : PropsWithChildren {
    var sideMenu: FC<*>
    var menuPosition: String
    var disableMenuGesture: Boolean
}

fun getSceneNameFromUrl(): String {
    val href = window.location.href
    return href.substringAfter("/#/").substringBefore("/")
}

val Navigator = FC<NavigatorProps> { props ->

    var initialSceneName: String by useState<String>("")
    var stack: List<Json> by useState<List<Json>>(emptyList())
    var sceneConfig: Json by useState<Json>(json {  })
    var showSideMenu: Boolean by useState<Boolean>(false)
    var touchX: dynamic by useState<Any>("")
    var browserHistory: List<String> by useState<List<String>>(emptyList())
    var firstTimeRender = useRef(true)

    val onBrowserHashChange = {
        window.location.reload()

        /*setState {
            val sceneName: String = getSceneNameFromUrl().ifEmpty { props.asDynamic().children[0].props.name as String }

            val pathVariables = sceneConfig[sceneName].unsafeCast<Json>()["pathVariables"]

            if (state.browserHistory.last() == sceneName) {
                handlePop()
            } else {
                handlePush(sceneName, getSceneProps(sceneName,
                    pathVariables as String
                ))
            }
        }*/
    }

    val _animatedValue = Animated.Value(0)

    val handlePop = {
        Animated.asDynamic().timing(_animatedValue, json {
            toValue = if (showSideMenu && props.menuPosition == "left") -screenWidth else screenWidth
            duration = 250
            useNativeDriver = true
        }).start {

            if (showSideMenu) {
                if (props.menuPosition == "left") {
                    _animatedValue.setValue(screenWidth)
                } else {
                    _animatedValue.setValue(-screenWidth)
                }

                showSideMenu = false
            } else {
                _animatedValue.setValue(0)
            }

            if (stack.size > 1) {
                stack = stack.slice(0 until stack.size - 1)

                if (Platform.OS == "web") {
                    browserHistory = browserHistory.slice(0 until browserHistory.size - 1)
                }

                if (Platform.OS == "web") {
                    history.back()
                }
            }
        }
    }

    val backAction = {
        if (stack.size == 1 && !showSideMenu) {
            BackHandler.exitApp()
        } else {
            handlePop()
        }

        true
    }

    val getSceneProps: dynamic = { sceneName: String, pathVariables: String ->
        val path = if (sceneName.isEmpty()) window.location.protocol + "://" + window.location.host + "/#/"
        else
            window.location.protocol + "://" + window.location.host + "/#/" + sceneName + "/"

        val filterParam = window.location.href.substring(path.length - 1)
        val values = filterParam.split("/")
        val variables = pathVariables.split("/")
        val urlProps = json {  }

        variables.forEachIndexed { index, s ->
            urlProps[s] = values[index]
        }

        urlProps
    }

    useEffectOnce {

        firstTimeRender.current = false

        if (Platform.OS == "web" && !window.location.href.contains("/#/")) {
            history.pushState(null, "", "/#/")
        }

        initialSceneName = if (Platform.OS == "web" && getSceneNameFromUrl().isNotEmpty()) getSceneNameFromUrl() else props.asDynamic().children[0].props.name
        sceneConfig = buildSceneConfig(props.asDynamic().children, props.sideMenu)

        if (Platform.OS == "web") {
            browserHistory = listOf(initialSceneName)
            val pathVariables = sceneConfig[initialSceneName].unsafeCast<Json>()["pathVariables"]

            if (pathVariables != undefined) {
                sceneConfig[initialSceneName].unsafeCast<Json>()["props"] = getSceneProps(initialSceneName,
                    pathVariables as String
                )
            }
        }

        stack = listOf(sceneConfig[initialSceneName].unsafeCast<Json>())
        showSideMenu = false

        if (Platform.OS == "web") {
            window.onhashchange = EventHandler {
                onBrowserHashChange()
            }
        }

        BackHandler.addEventListener(
            "hardwareBackPress",
            backAction()
        )
    }

    useEffectOnce {
        BackHandler.removeEventListener(
            "hardwareBackPress",
            backAction()
        )
    }

    val onSwipeLeft = {
        if (showSideMenu) {
            handlePop()

            showSideMenu = !showSideMenu
        }
    }

    val onSwipeRight = {
        if (showSideMenu) {
            handlePop()

            showSideMenu = !showSideMenu
        }
    }

    val changeUrl = { urlata: String, sceneName: String ->
        if (sceneName != "krnMenu") {
            if (Platform.OS == "web") {
                if (initialSceneName == sceneName) {
                    if (urlata.isNotEmpty())
                        history.pushState(null, "", "/#$urlata")
                    else
                        history.pushState(null, "", "/#/")
                } else {
                    if (urlata.isNotEmpty())
                        history.pushState(null, "", "/#/$sceneName$urlata")
                    else
                        history.pushState(null, "", "/#/$sceneName")
                }
            }
        }
        /*val path = if (state.initialSceneName == sceneName) window.location.protocol + "://" + window.location.host + "/#/"
        else
            window.location.protocol + "://" + window.location.host + "/#/" + sceneName + "/"

        val filterParam = window.location.href.substring(path.length - 1)
        return filterParam.split("/")*/
    }

    useEffect(stack) {
        if (!firstTimeRender.current!!) {
            if (stack.last()["key"].toString() == "krnMenu") {
                if (props.menuPosition == "left") {
                    _animatedValue.setValue(-screenWidth)
                } else {
                    _animatedValue.setValue(screenWidth)
                }
            } else {
                _animatedValue.setValue(screenWidth)
            }

            Animated.asDynamic().timing(_animatedValue, json {
                toValue = 0
                duration = 250
                useNativeDriver = true
            }).start()
        }
    }

    val handlePush = { sceneName: String, childProps: dynamic ->
        showSideMenu = sceneName == "krnMenu"

        if (childProps != undefined && childProps != "") {
            var propsParams = ""
            val childPropsNames = Object.getOwnPropertyNames(unsafeCast(childProps as Any))

            for (propName in childPropsNames) {
                propsParams += "/" + childProps[propName]
            }

            changeUrl(propsParams, sceneName)
        }

        sceneConfig[sceneName].unsafeCast<Json>()["props"] = childProps

        if (Platform.OS == "web") {
            browserHistory = browserHistory + listOf(sceneName)
        }

        stack = if (stack.last()["key"].toString() == "krnMenu") {
                stack.slice(0 until stack.size - 1) + listOf(sceneConfig[sceneName].unsafeCast<Json>())
            } else {
                stack + listOf(
                    sceneConfig[sceneName].unsafeCast<Json>()
                )
            }
    }



    val handleShowMenu = {
        handlePush("krnMenu") {}
    }

    View {
        style = json {
            asDynamic().flex = 1
            flexDirection = "row"
        }
        asDynamic().onTouchStart = { e: dynamic ->
            val gestureDisabled: dynamic = props.disableMenuGesture
            if (gestureDisabled == undefined || !gestureDisabled) {
                val pageX = e.nativeEvent.pageX
                touchX = pageX
            }
        }
        asDynamic().onTouchEnd = { e: dynamic ->
            val gestureDisabled: dynamic = props.disableMenuGesture
            if (gestureDisabled == undefined || !gestureDisabled) {
                val positionX = e.nativeEvent.pageX
                val range = screenWidth / 6

                if (positionX - touchX > range) {
                    onSwipeRight()
                } else if (touchX - positionX > range) {
                    onSwipeLeft()
                }
            }
        }

        if (!stack.isNullOrEmpty()) {
            stack.forEachIndexed { index, json ->
                val sceneStyles = StyleSheet.compose(
                    json {
                        asDynamic().flex = 1
                    },
                    StyleSheet.asDynamic().absoluteFillObject
                )

                if (index == stack.size - 1 && index > 0) {
                    sceneStyles.push(json {
                        transform = arrayOf(
                            json {
                                translateX = _animatedValue
                            }
                        )
                    })
                }

                // Fragments
                AnimatedView {
                    style = sceneStyles
                    asDynamic().key = json["key"].toString()

                    /*json.asDynamic().component.unsafeCast<FC<Props>> {
                        asDynamic().navigator = object : NavigatorFunctions {
                            override var pop = handlePop
                            override val push = handlePush
                            override var showMenu = handleShowMenu
                        }

                        if (json["props"] != undefined) {
                            val childProps = json["props"].unsafeCast<JSON>()
                            val childPropsNames = childProps.getOwnPropertyNames()
                            for (propName in childPropsNames) {
                                asDynamic()[propName] = window.asDynamic().decodeURI(json["props"].asDynamic()[propName])
                            }
                        }

                    }*/

                    /*child(json.asDynamic().component.unsafeCast<FC<Props>>()) {
                        if (json["props"] != undefined) {
                            val childProps = json["props"].unsafeCast<JSON>()
                            val childPropsNames = childProps.getOwnPropertyNames()
                            for (propName in childPropsNames) {
                                this.attrs.asDynamic()[propName] = window.asDynamic().decodeURI(json["props"].asDynamic()[propName])
                            }
                        }

                        this.attrs {
                            asDynamic().navigator = object : NavigatorFunctions {
                                override var pop = handlePop
                                override val push = handlePush
                                override var showMenu = handleShowMenu
                            }
                        }
                    }*/
                }
            }
        }

    }
}
