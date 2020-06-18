import React, {useState} from 'react';
import {
    Text,
    View,
    TextInput,
    TouchableOpacity,
    ActivityIndicator,
    ScrollView,
    Alert
} from 'react-native';
import {ip as ip} from '../../../ipconfig.json';
import styles from '../../style/addaccount';

export default function AddAccount({route, navigation}) {
    const {selectRole, authorization} = route.params;
    const [name, setName] = useState('');
    const [checkName, setCheckName] = useState(false);
    const [email, setEmail] = useState('');
    const [checkEmail, setCheckEmail] = useState(false);
    const [phone, setPhone] = useState('');
    const [checkPhone, setCheckPhone] = useState(false);
    const [password, setPassword] = useState('');
    const [checkPassword, setCheckPassword] = useState(false);
    const [submitPwd, setSubmitPwd] = useState('');
    const [checkSubmitPwd, setCheckSubmitPwd] = useState(false);
    const [isLoading, setLoading] = useState(false);

    const roleList = new Map([
        ["USER", "Khách hàng"],
        ["MEDIC", "Nhân viên y tế"],
        ["ADMIN", "Nhân viên quản lý"]
    ]);

    function createAccount() {
        setLoading(true);
        fetch('http://'+ ip + ':8080/sign-up', {
            method: 'POST',
            headers: {
                Accept: '*/*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name: name,
                username: phone,
                password: password,
                email: email,
                role: selectRole
            })
        })
            .then((response) => {
                if (response.status === 201) {
                    setLoading(false);
                    Alert.alert(
                        "Thông báo",
                        "Tạo tài khoản thành công!",
                        [
                            {
                                text: "OK",
                                onPress: () => navigation.pop(1)
                            }
                        ]
                    );
                }
                else {
                    setLoading(false);
                    Alert.alert(
                        "Thông báo",
                        "Số điện thoại đã được đăng ký!",
                        [
                            {
                                text: "OK",
                                style: "cancel"
                            }
                        ]
                    );
                }
            })
            .catch((error) => {
                setLoading(false);
                Alert.alert(
                    "Thông báo",
                    "Đã xảy ra lỗi!",
                    [
                        {
                            text: "OK",
                            style: "cancel"
                        }
                    ]
                );
            })
            .finally(() => setLoading(false))
    }

    function checkNameFormat () {
        if (name.length === 0) 
            return false;
        else 
            return true;
    }

    function checkEmailFormat() {
        const regex = RegExp(/^[a-z][a-z0-9_\.]{1,32}@[a-z0-9]{2,}(\.[a-z0-9]{2,4}){1,2}$/);
        return regex.test(email); 
    }

    function checkPhoneFormat() {
        const regex = RegExp(/(0[0-9])+([0-9]{8})\b/);
        return regex.test(phone);
    }

    function checkPasswordFormat() {
        const regex = RegExp(/^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$/);
        return regex.test(password); 
    }
    function checkSubmitPwdFormat() {
        return ((password === submitPwd) && (submitPwd.length != 0));
    }

    function checkInput() {
        if (checkNameFormat() && checkEmailFormat() && checkPhoneFormat() && checkPasswordFormat()
            && checkSubmitPwdFormat())
            return true;
        else {
            setCheckName(true);
            setCheckEmail(true);
            setCheckPhone(true);
            setCheckPassword(true);
            setCheckSubmitPwd(true);
            return false;
        }
    }

    function errorNotice() {
        if ((name.length === 0) || (email.length === 0) || (phone.length === 0) 
            || (password.length === 0) || (submitPwd.length === 0)) {
                Alert.alert(
                    "Thông báo",
                    "Bạn phải nhập đầy đủ các thông tin!",
                    [
                        {
                            text: "OK",
                            style: "cancel"
                        }
                    ]
                );
        } else if (!checkPhoneFormat()) {
            Alert.alert(
                "Thông báo",
                "Số điện thoại không hợp lệ!",
                [
                    {
                        text: "OK",
                        style: "cancel"
                    }
                ]
            );
        }else if (!checkEmailFormat()) {
            Alert.alert(
                "Thông báo",
                "Email không hợp lệ!",
                [
                    {
                        text: "OK",
                        style: "cancel"
                    }
                ]
            );
        }else if (!checkPasswordFormat()) {
            Alert.alert(
                "Thông báo",
                "Mật khẩu không đúng định dạng!",
                [
                    {
                        text: "OK",
                        style: "cancel"
                    }
                ]
            );
        } else if (!checkSubmitPwdFormat()) {
            Alert.alert(
                "Thông báo",
                "Xác nhận mật khẩu không khớp!",
                [
                    {
                        text: "OK",
                        style: "cancel"
                    }
                ]
            );
        }
    }

    return (
        <ScrollView contentContainerStyle={styles.container}>
            {isLoading &&
            <View style={[styles.loading, {backgroundColor: 'rgba(192,192,192,0.7)'}]}></View>
            }
            {isLoading &&
            <View style={styles.loading}>
                <ActivityIndicator size={100} color='#191970'/>
            </View>
            }

            <Text style={styles.title}>{"Tạo tài khoản " + roleList.get(selectRole).toLowerCase()}</Text>
            <TextInput
                onChangeText={(text) => {
                    setCheckName(true);
                    setName(text);
                }}
                underlineColorAndroid={(checkName && !checkNameFormat()) ? 'red' : '#191970'}
                style={styles.textInput}
                placeholder={'Họ và tên'}
            />
            <TextInput
                onChangeText={(text) => {
                    setCheckEmail(true);
                    setEmail(text.toLocaleLowerCase());
                }}
                underlineColorAndroid={(checkEmail && !checkEmailFormat()) ? 'red' : '#191970'}
                style={styles.textInput}
                style={styles.textInput}
                placeholder={'Email'}
            />
            <TextInput
                onChangeText={(text) => {
                    setCheckPhone(true);
                    setPhone(text);
                }}
                underlineColorAndroid={(checkPhone && !checkPhoneFormat()) ? 'red' : '#191970'}
                style={styles.textInput}
                placeholder={'Số điện thoại'}
            />
            <TextInput
                onChangeText={(text) => {
                    setCheckPassword(true);
                    setPassword(text);
                }}
                underlineColorAndroid={(checkPassword && !checkPasswordFormat()) ? 'red' : '#191970'}
                style={[styles.textInput, {marginBottom: 5}]}
                placeholder={'Mật khẩu'}
                secureTextEntry={true}
            />
            <Text style={styles.require}>Mật khẩu tối thiểu 8 ký tự, ít nhất 1 chữ số và 1 chữ cái viết hoa.</Text>
            <TextInput
                onChangeText={(text) => {
                    setCheckSubmitPwd(true);
                    setSubmitPwd(text);
                }}
                underlineColorAndroid={(checkSubmitPwd && !checkSubmitPwdFormat()) ? 'red' : '#191970'}
                style={styles.textInput}
                placeholder={'Xác nhận mật khẩu'}
                secureTextEntry={true}
            />
            <TouchableOpacity
                style={styles.btnSignup}
                onPress={() => {
                    if (checkInput())
                        createAccount();
                    else
                        errorNotice();
                }}
            >
                <Text style={styles.text}>Tạo tài khoản</Text>
            </TouchableOpacity>
        </ScrollView>
    );
}