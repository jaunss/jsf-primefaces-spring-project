package com.joaogcm.jsf.primefaces.spring.project.access;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum Permission {
	ADMIN("ADMIN", "Administrator"),
	USER("USER", "Default user"),
	REGISTER_ACCESS("REGISTER_ACCESS", "Register access"),
	FINANCIAL_ACESS("FINANCIAL_ACCESS", "Financial access"),
	MESSAGE_ACCESS("MESSAGE_ACCESS", "Message access");

	private String value = "";
	private String description = "";

	private Permission(String value, String description) {
		this.value = value;
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return getValue();
	}

	public static List<Permission> getListPermission() {
		List<Permission> permissions = new ArrayList<Permission>();

		for (Permission perm : Permission.values()) {
			permissions.add(perm);
		}

		Collections.sort(permissions, new Comparator<Permission>() {

			@SuppressWarnings("deprecation")
			@Override
			public int compare(Permission o1, Permission o2) {
				return new Integer(o1.ordinal()).compareTo(o2.ordinal());
			}

		});

		return permissions;
	}
}