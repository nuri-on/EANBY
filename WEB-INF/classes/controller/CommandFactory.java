package controller;

import controller.action.*;

public class CommandFactory {

	private CommandFactory() {
	}

	public static CommandFactory getInstance() {
		return new CommandFactory();
	}

	/*
	 * 수행해야할 명령어에 해당하는 Action객체를 생성.
	 */
	public Action getAction(String command) throws IllegalCommandException {
		Action action = null;

		 if (command.equals("appDetail")) {
			action = new ActionAppDetail();
		} else if (command.equals("appInsert")) {
			action = new ActionAppInsert();
		} else if (command.equals("appList")) {
			action = new ActionAppList();
		} else if (command.equals("joinInsert")) {
			action = new ActionJoinInsert();
		} else if (command.equals("checkEmail")) {
			action = new ActionCheckEmail();
		} else if (command.equals("appList")) {
				action = new ActionAppList();
		} else if (command.equals("Mlogin")) {
			action = new ActionMLogin();
		} else if (command.equals("login")) {
			action = new ActionLogin();
		} else if (command.equals("logout")) {
			action = new ActionLogout();
		} else if (command.equals("mySpec")) {
			action = new ActionMySpec();
		} else if (command.equals("specInsert")) {
			action = new ActionSpecInsert();
		} else if (command.equals("specElementDelete")) {
			action = new ActionSpecElementDelete();
		} else if (command.equals("userAppInsert")) {
			action = new ActionUserAppInsert();
		} else if (command.equals("userAppList")) {
			action = new ActionUserAppList();
		} else if (command.equals("userAppUpdate")) {
			action = new ActionUserAppUpdate();
		} else if (command.equals("getPhoto")) {
			action = new ActionGetPhoto();
		} else {
			throw new IllegalCommandException("잘못된 실행명령입니다. 다른 명령을 실행해 주십시요");
		}

		return action;
	}
}

