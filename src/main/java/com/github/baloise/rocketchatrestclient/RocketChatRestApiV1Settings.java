package com.github.baloise.rocketchatrestclient;

import java.io.IOException;

import com.github.baloise.rocketchatrestclient.model.Setting;

public class RocketChatRestApiV1Settings {

	private RocketChatClientCallBuilder callBuilder;

	protected RocketChatRestApiV1Settings(RocketChatClientCallBuilder callBuilder) {
		this.callBuilder = callBuilder;
	}
	
	public Setting[] list() throws IOException{
		RocketChatQueryParams rocketChatQueryParams = new RocketChatQueryParams("count", "0");
		RocketChatClientResponse res =
			this.callBuilder.buildCall(RocketChatRestApiV1.SettingsGetAll, rocketChatQueryParams);
		
		if (!res.isSuccessful())
			throw new IOException(
				"The call to get Settings was unsuccessful: \"" + res.getError() + "\"");
		
		return res.getSettings();
	}

	public Setting getById(String settingId) throws IOException {
		RocketChatQueryParams rocketChatQueryParams = new RocketChatQueryParams(
				RocketChatClientCallBuilder.CALL_METHOD_NAME_ARGUMENTS_KEY, settingId);
		RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.SettingGetById,
				rocketChatQueryParams);

		if (!res.isSuccessful())
			throw new IOException("The call to get Settings was unsuccessful: \"" + res.getError() + "\"");

		Setting setting = new Setting();
		setting.setId(res.getId());
		setting.setValue(res.getValue());
		return setting;

	}

	public void setById(String settingId, String value) throws IOException {
		RocketChatQueryParams rocketChatQueryParams = new RocketChatQueryParams(
				RocketChatClientCallBuilder.CALL_METHOD_NAME_ARGUMENTS_KEY, settingId);

		Setting updateOrAdd = new Setting();
		updateOrAdd.setValue(value);

		RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.SettingSetById,
				rocketChatQueryParams, updateOrAdd);
		if (!res.isSuccessful())
			throw new IOException("The call to set Setting was unsuccessful: \"" + res.getError() + "\"");
	}

}
