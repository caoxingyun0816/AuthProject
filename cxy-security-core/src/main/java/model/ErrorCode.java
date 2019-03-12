package model;

public enum ErrorCode implements ErrorCodeInterface {

	SUCCESS(0, "Success"),

	ERROR(1, "Error"),

	WRONG_ONSERVER(2, "未知错误！"),

	WRONG_PARAM(3, "非法参数！"),

	FAIL_INPUTSTEAM(4, "获取输入流失败！"),

	FAIL_IMPORTEXCEL(5, "导入Excel失败！"),

	FAIL_EXPORTEXCEL(6, "导出Excel失败！"),

	FAIL_SAVE(7, "新增失败！"),

	FAIL_UPDATE(8, "更新失败！"),

	FAIL_DELETE(9, "删除失败！"),

	MANY_DATA(10, "查询结果大于1条！"),

	REPEAT_DATA(11, "重复数据！"),

	WRONG_JSON(12, "JSON格式错误！");

	private int code;
	private String message;

	ErrorCode(int code, String message) {
		this.message = message;
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
