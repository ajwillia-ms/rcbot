/*
 * Copyright 2006-2013 Andrew Williams.
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

package com.rectang.rcbot.module;

import com.rectang.rcbot.*;

import java.util.*;

import org.headsupdev.irc.IRCConnection;
import org.headsupdev.irc.IRCUser;
import org.headsupdev.irc.IRCServiceManager;

/* watch what the bot says too? how?
 */

/**
 * A module for watching who is around
 *
 */
public class Seen extends RCBotCommand {

  public Seen(RCBot bot, IRCServiceManager manager) {
    super(bot, manager);
  }

  public String getId() {
    return "seen";
  }

  public String getTitle() {
    return "Watch users come and go and report when folk were present";
  }

  public Collection getCommands() {
    return NO_COMMANDS;
  }

  public void onSubCommand(String command, String channel, IRCUser user,
                           String message, IRCConnection conn) {
    String match = getStore(bot).getString(message);
    if (match == null || match.equals("")) {
      conn.sendMessage(channel, "I cannot remember seeing " + message);
    } else {
      String parts[] = match.split(":");

      long time = 0;
      try {
        time = Long.parseLong(parts[0]);
      } catch (NumberFormatException e) {
        /* report below that we cannot remember */
      }
      if (time == 0) {
        conn.sendMessage(channel, "I have seen " + message + ", but I cannot remember when");
      } else {
          String timeOffset = StringUtils.formatTimeOffset((System.currentTimeMillis() - time) / 1000);
          if (parts.length < 3) {
              conn.sendMessage(channel, "I have seen " + message + " on " + parts[1] + " " + timeOffset + " ago");
          } else {
              String action = match.substring(parts[0].length() + 1 + parts[1].length() + 1);
              if (action.equals("joining")) {
                conn.sendMessage(channel, "I last saw " + message + " joining " + parts[1] + " " + timeOffset + " ago");
              } else {
                conn.sendMessage(channel, "I last saw " + message + " on " + parts[1] + " " + timeOffset + " ago " +
                    action);
              }
          }
      }
    }
  }

  public String getHelp(String channel) {
    return "Seen is a module for finding out when people were last online.\n" +
        "  To see when someone was last around use \"seen username\".";
  }

  public void onMissingCommand( String command, String channel, IRCUser user, IRCConnection conn ) {
    /* cannot be called, we accept NO_COMMANDS */
  }
}
