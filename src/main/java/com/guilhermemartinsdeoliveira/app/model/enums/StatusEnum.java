package com.guilhermemartinsdeoliveira.app.model.enums;

public enum StatusEnum {

	IN_PROGRESS(0) {
		@Override
		public String toString() {
			return "IN_PROGRESS";
		}
	},
	FINISHED(1) {
		@Override
		public String toString() {
			return "FINISHED";
		}
	};

	private int value;

	private StatusEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
