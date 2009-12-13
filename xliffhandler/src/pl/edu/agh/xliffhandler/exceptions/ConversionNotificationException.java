/*
 * ConversionNotificationException.java
 *
 * Copyright (C) 2006. Lingotek, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  
 * 02110-1301, USA
 */

package pl.edu.agh.xliffhandler.exceptions;

/**
 * @author Maria Szymczak, Weldon Whipple &lt;weldon@lingotek.com&gt;
 */

public class ConversionNotificationException extends ConversionException {

	private static final long serialVersionUID = 1L;

	/**
     * No-argument version of ConversionNotificationException constructor
     */
    public ConversionNotificationException() { }
    
    /**
     * ConversionNotificationException constructor that takes a message
     * string as an argument
     * 
     * @param msg Informational message about the exception.
     */
    public ConversionNotificationException(String msg) {
        super(msg);
    }
}
