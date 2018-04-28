/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jbpm.casemgmt.demo.enrichment;

/**
 * This class was automatically generated by the data modeler tool.
 */

public class DocumentType implements java.io.Serializable {

	static final long serialVersionUID = 1L;

	private java.lang.String name;
	private java.lang.Boolean uploaded;
	private java.lang.Boolean approved;
	private java.lang.String type;

	private java.lang.Boolean rejected;

	public DocumentType() {
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.Boolean getUploaded() {
		return this.uploaded;
	}

	public void setUploaded(java.lang.Boolean uploaded) {
		this.uploaded = uploaded;
	}

	public java.lang.Boolean getApproved() {
		return this.approved;
	}

	public void setApproved(java.lang.Boolean approved) {
		this.approved = approved;
	}

	public java.lang.String getType() {
		return this.type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public java.lang.Boolean getRejected() {
		return this.rejected;
	}

	public void setRejected(java.lang.Boolean rejected) {
		this.rejected = rejected;
	}

	public DocumentType(java.lang.String name, java.lang.Boolean uploaded,
			java.lang.Boolean approved, java.lang.String type,
			java.lang.Boolean rejected) {
		this.name = name;
		this.uploaded = uploaded;
		this.approved = approved;
		this.type = type;
		this.rejected = rejected;
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DocumentType other = (DocumentType) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }
}