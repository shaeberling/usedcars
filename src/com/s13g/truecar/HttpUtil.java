/*
 * Copyright 2016 Sascha Häberling
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.s13g.truecar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;

/**
 * HTTP utilities.
 */
final class HttpUtil {
  HttpUtil() {
  }

  /**
   * Load the URL and return the string of the response.
   *
   * @param urlStr the URL to request.
   * @return If the request was successful, returns the string contents. Otherwise empty.
   */
  Optional<String> loadUrl(String urlStr) {
    try {
      URLConnection urlConnection = new URL(urlStr).openConnection();
      BufferedReader in = new BufferedReader(new InputStreamReader(
          urlConnection.getInputStream()));
      StringBuilder result = new StringBuilder();
      String line;
      while ((line = in.readLine()) != null) {
        result.append(line);
        result.append('\n');
      }
      in.close();
      return Optional.of(result.toString());
    } catch (IOException ex) {
      System.err.println("Cannot load URL: " + ex.getMessage());
      return Optional.empty();
    }
  }
}
