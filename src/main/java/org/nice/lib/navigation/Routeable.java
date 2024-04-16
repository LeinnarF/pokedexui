package org.nice.lib.navigation;

import javax.swing.*;

/**
 * A Panel which will contain the content that will be shown depending on the active route.
 */
public abstract class Routeable extends JPanel {
    /** Called when the route is activated
     * @param data Optional data that can be passed to the routeable during navigation
     */
    public abstract void onNavigationEnter( Object... data);

    /** Called when the route is about to be deactivated. Use this to dispose side effects.
     * @param newRoute The new route id
     */
    public abstract void onNavigationExit(String newRoute);
}
