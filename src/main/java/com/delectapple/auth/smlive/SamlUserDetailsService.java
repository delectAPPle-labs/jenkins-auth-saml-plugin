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

import hudson.model.User;
import hudson.security.SecurityRealm;
import jenkins.model.Jenkins;
import jenkins.security.LastGrantedAuthoritiesProperty;
import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.List;

/**
 * This service is responsible for restoring UserDetails object by userId
 */
public class SamlUserDetailsService implements UserDetailsService {

  public SamlUserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

    // try to obtain user details from current authentication details
    Authentication auth = Jenkins.getAuthentication();
    if (auth != null && username.compareTo(auth.getName()) == 0 && auth instanceof SamlAuthenticationToken) {
      return (SamlUserDetails) auth.getDetails();
    }

    // try to rebuild authentication details based on data stored in user storage
    User user = User.get(username);
    if (user == null) {
      throw new UsernameNotFoundException(username);
    }

    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    authorities.add(SecurityRealm.AUTHENTICATED_AUTHORITY);

    if (username.compareTo(user.getId()) == 0) {
      LastGrantedAuthoritiesProperty lastGranted = user.getProperty(LastGrantedAuthoritiesProperty.class);
      if (lastGranted != null) {
        for (GrantedAuthority a : lastGranted.getAuthorities()) {
          if (a != SecurityRealm.AUTHENTICATED_AUTHORITY) {
            SamlGroupAuthority ga = new SamlGroupAuthority(a.getAuthority());
            authorities.add(ga);
          }
        }
      }
    }

    SamlUserDetails userDetails = new SamlUserDetails(user.getId(), authorities.toArray(new GrantedAuthority[0]));
    return userDetails;
  }
}
