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
import org.acegisecurity.userdetails.UserDetails;

public class SamlUserDetails implements UserDetails {

  private static final long serialVersionUID = 2L;

  private final String username;
  private final GrantedAuthority[] authorities;

  public SamlUserDetails(String username, GrantedAuthority[] authorities) {
    checkSamlUserDetails(username, authorities);
    this.username = username;
    this.authorities = authorities;
  }

  public GrantedAuthority[] getAuthorities() {
    return authorities;
  }

  public String getPassword() {
    return null;
  }

  public String getUsername() {
    return username;
  }

  public boolean isAccountNonExpired() {
    return true;
  }

  public boolean isAccountNonLocked() {
    return true;
  }

  public boolean isCredentialsNonExpired() {
    return true;
  }

  public boolean isEnabled() {
    return true;
  }

  private void checkSamlUserDetails(String username, GrantedAuthority[] authorities) {
    if (authorities == null || username == null) {
      throw new IllegalArgumentException();
    }
  }

  public SamlUserDetails immutableSamlUserDetails() {
    return new SamlUserDetails(username, authorities);
  }
}
