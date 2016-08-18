/*******************************************************************************
 *
 * Copyright (c) 2016, delectAPPle labs, LLC. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with This program.  If not, see <https://www.gnu.org/licenses/gpl.txt>.
 *
 * @author  delectAPPle labs
 * @version 1.0 
 * @since   January 1, 2010
 *
 *******************************************************************************/

package com.delectapple.auth.smlive;

import hudson.Util;

import org.kohsuke.stapler.DataBoundConstructor;

/**
 * Simple immutable data class to hold the optional encryption data section
 * of the plugin's configuration page
 */
public class SamlEncryptionData {
  private final String keystorePath;
  private final String keystorePassword;
  private final String privateKeyPassword;

  @DataBoundConstructor
  public SamlEncryptionData(String keystorePath, String keystorePassword, String privateKeyPassword) {
    this.keystorePath = Util.fixEmptyAndTrim(keystorePath);
    this.keystorePassword = Util.fixEmptyAndTrim(keystorePassword);
    this.privateKeyPassword = Util.fixEmptyAndTrim(privateKeyPassword);
  }

  public String getKeystorePath() {
    return keystorePath;
  }

  public String getKeystorePassword() {
    return keystorePassword;
  }

  public String getPrivateKeyPassword() {
    return privateKeyPassword;
  }
}