package org.nice.lib.navigation;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/** Makes navigating between different panels easier.
 *  DO NOT SET the layoutManager of this class!
 */
public class NavigationPanel extends JPanel  {

    private final CardLayout cardLayout = new CardLayout();
    private final Map<String, Routeable> routeMap = new HashMap<>();

    private NavRoute activeRoute;

    /**
     * @return the current active route.
     */
    public NavRoute getActiveRoute() {
        return activeRoute;
    }

    public final org.nice.lib.navigation.Event<NavRoute> onNavigationChange = new Event<>();


    private void setActiveRoute(NavRoute newRoute, Object... data) {
        if(activeRoute != null) {
            activeRoute.component().onNavigationExit(newRoute.route());
        }
        activeRoute = newRoute;
        activeRoute.component().onNavigationEnter(data);
        cardLayout.show(this, newRoute.route());
    }


    public NavigationPanel()  {
        initialize();
    }

    public NavigationPanel(NavRoute[] initRoute)  {
        initialize(initRoute);
    }


    /** Adds a new route-component pair
     */
    public void addRoute(String route, Routeable component) {
        routeMap.put(route, component);
        add(component, route);
    }

    /** Shows the panel to the specific route.
     * @param route the route to switch
     */
    public void navigateTo(String route, Object... data) {
        if(Objects.equals(activeRoute.route(), route)) {
            return;
        }
        var componentClass = routeMap.get(route);
        setActiveRoute(new NavRoute(componentClass, route), data);
        onNavigationChange.invoke(activeRoute);
    }

    private void initialize(NavRoute... initRoute) {
        setLayout(cardLayout);
        for(var r : initRoute) {
            routeMap.put(r.route(), r.component());
            add(r.component(), r.route());
        }
        if(initRoute.length > 0) {
            NavRoute firstRoute = initRoute[0];
            setActiveRoute(firstRoute);
        }





    }
}
