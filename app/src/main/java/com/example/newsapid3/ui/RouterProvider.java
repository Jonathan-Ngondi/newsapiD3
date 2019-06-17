package com.example.newsapid3.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
public interface RouterProvider {

    Router getRouter();

    Controller initialScreen();
}
