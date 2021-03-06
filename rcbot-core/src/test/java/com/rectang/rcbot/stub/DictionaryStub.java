/*
 * Copyright 2006-2012 Andrew Williams.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rectang.rcbot.stub;

import com.rectang.rcbot.Dictionary;

/**
 * Simple dictionary stub for testing
 */
public class DictionaryStub implements Dictionary {
  public String lookupWord(String word) {
    return null;
  }

  public String lookupWord(String dict, String word) {
    return null;
  }

  public String matchWord(String word) {
    return null;
  }

  public String matchWord(String dict, String word) {
    return null;
  }

  public String randomWord() {
    return null;
  }

  public String randomWord(String dict) {
    return null;
  }
}
