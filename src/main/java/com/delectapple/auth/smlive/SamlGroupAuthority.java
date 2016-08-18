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

import org.acegisecurity.GrantedAuthority;

/**
 * Authority class, represents a group receved in SAML response
 */
public class SamlGroupAuthority implements GrantedAuthority {

  private final String group;

  public SamlGroupAuthority(String group) {
    this.group = group;
  }

  public String getAuthority() {
    return this.group;
  }

  @Override
  public String toString() {
    return this.group;
  }
}
