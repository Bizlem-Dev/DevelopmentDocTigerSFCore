package com.dto;

public class TemplateFileVO {

	private String temaplateName;
	private String temaplatePath;
	private String outputFormat;
	private String inputFormat;
	private String uploadDocumentLanguageType;
	
	public String getTemaplateName() {
		return temaplateName;
	}
	public void setTemaplateName(String temaplateName) {
		this.temaplateName = temaplateName;
	}
	public String getTemaplatePath() {
		return temaplatePath;
	}
	public void setTemaplatePath(String temaplatePath) {
		this.temaplatePath = temaplatePath;
	}
	public String getOutputFormat() {
		return outputFormat;
	}
	public void setOutputFormat(String outputFormat) {
		this.outputFormat = outputFormat;
	}
	public String getInputFormat() {
		return inputFormat;
	}
	public void setInputFormat(String inputFormat) {
		this.inputFormat = inputFormat;
	}
	public String getUploadDocumentLanguageType() {
		return uploadDocumentLanguageType;
	}
	public void setUploadDocumentLanguageType(String uploadDocumentLanguageType) {
		this.uploadDocumentLanguageType = uploadDocumentLanguageType;
	}
}
