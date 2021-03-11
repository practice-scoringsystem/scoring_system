/**
 *
 */
function check() {
	if (form.name.value == "") {
		//条件に一致する場合
		alert("名前を入力してください"); //エラーメッセージを出力
		return false; //送信ボタン本来の動作をキャンセルします
	} else if (!form.name.value.match(/^[A-Za-z0-9]*$/)) {
		alert("名前は半角英数字のみです"); //エラーメッセージを出力
		return false; //送信ボタン本来の動作をキャンセルします
	} else if (form.password.value == "") {
		alert("パスワードを入力してください");
		return false;
	} else if (!form.password.value.match(/^([a-zA-Z0-9]{8,})$/)) {
		alert("パスワードは半角英数字で8文字以上に設定してください");
		return false;
	} else if (form.confirmPassword.value == "") {
		alert("確認用のパスワードを入力してください");
		return false;
	} else if (!form.confirmPassword.value.match(/^([a-zA-Z0-9]{8,})$/)) {
		alert("パスワードは半角英数字で8文字以上に設定してください");
		return false;
	} else if (form.password.value != form.confirmPassword.value){
      alert("パスワードと確認用パスワードが一致しません"); // 一致していなかったら、エラーメッセージを表示する
      return false;
	} else {
		//条件に一致しない場合(入力されている場合)
		return true; //送信ボタン本来の動作を実行します
	}
}