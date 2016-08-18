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

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hudson.Extension;
import hudson.model.Descriptor;
import hudson.security.SecurityRealm;
import hudson.security.csrf.CrumbExclusion;

@Extension
public class SamlCrumbExclusion extends CrumbExclusion {
    private static final Logger LOG = Logger.getLogger(SamlCrumbExclusion.class.getName());

    @Override
    public boolean process(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String pathInfo = request.getPathInfo();
        if(shouldExclude(pathInfo)) {
            chain.doFilter(request, response);
            return true;
        }
        return false;
    }

    private static boolean shouldExclude(String pathInfo) {
        if(pathInfo == null) {
            return false;
        }
        if(pathInfo.indexOf(SamlSecurityRealm.CONSUMER_SERVICE_URL_PATH) == 1) {
            LOG.fine("SamlCrumbExclusion.shouldExclude excluding '" + pathInfo + "'");
            return true;
        } else {
            LOG.finer("SamlCrumbExclusion.shouldExclude keeping '" + pathInfo + "'");
            return false;
        }
    }
}
