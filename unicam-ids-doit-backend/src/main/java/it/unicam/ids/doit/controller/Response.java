package it.unicam.ids.doit.controller;

import com.fasterxml.jackson.annotation.JsonView;

import it.unicam.ids.doit.model.json.JsonViews;

@JsonView (JsonViews.Any.class)
public class Response<T>
{
	public enum Status
	{
		SUCCESS, ERROR;
	}
	private Status status;
	private T data;
	private String errorMessage;
	
	public Response()
	{
		super();
	}

	public Response(Status status, T data, String errorMessage)
	{
		super();
		this.status = status;
		this.data = data;
		this.errorMessage = errorMessage;
	}

	public static <T> Response<T> of(Status status, T data, String errorMessage)
	{
		return new Response<T>(status, data, errorMessage);
	}
	public static <T> Response<T> of(T data)
	{
		return of(Status.SUCCESS, data, null);
	}
	public static Response<Void> of(Throwable e)
	{
		return of(Status.ERROR, null, e.getMessage());
	}
	
	public Status getStatus()
	{
		return status;
	}
	public void setStatus(Status status)
	{
		this.status = status;
	}
	
	public T getData()
	{
		return data;
	}
	public void setData(T data)
	{
		this.data = data;
	}
	
	public String getErrorMessage()
	{
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}
	
}
