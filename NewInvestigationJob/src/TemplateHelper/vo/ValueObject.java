/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TemplateHelper.vo;

/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */

/**
 * The ValueObject class must be the superclass of any value object that is to be used for data transfer.
 * 
 * @author AHIS
 */

public abstract class ValueObject implements java.io.Serializable
{
	private static final long serialVersionUID = 0002L;

	// String describe() : Provides a textual version of description and state.
	// String describeAsXMLDocument (java.lang.String fieldName) : Provides XML Text for a given instance.

	// public boolean equals(java.lang.Object obj) << primary key
	// public int hashCode() << primary key
	// All other accessors and mutators in the subclass
}
