import React, {useState} from 'react';
import {
    Text, 
    View,
    Image,
    TextInput,
    ScrollView,
    ActivityIndicator,
    Alert
} from 'react-native';
import {Picker} from '@react-native-community/picker';
import styles from '../../style/personalinformation';

export default function PersonalInformation({route, params}) {
    const {userId, authorization} = route.params;
    const [user, setUser] = useState({});
    const [accountImg, setAccountImg] = useState(require('../../image/personalinformation/account.png'));
    const [editableInfo, setEditableInfo] = useState(false);
    const [gender, setGender] = useState("Nam");
    const [isLoading, setLoading] = useState(true);

    React.useEffect(() => {
        fetch('http://192.168.56.1:8080/users/' + userId, {
            method: 'GET',
            headers: {
                Accept: '*/*',
                'Content-Type': 'application/json',
                Authorization: authorization
            }
        })
            .then((response) => response.json())
            .then((json) => {
                setUser(json);
                return json;
            })
            .then((json) => setGender(json.gender))
            .catch((error) => 
                Alert.alert(
                    "Thông báo",
                    "Lỗi kết nối!",
                    [
                        {
                            text: "OK",
                            style: "cancel"
                        }
                    ]
                )
            )
            .finally(() => setLoading(false))
    }, []);

    function changeDateFormat(date, mode) {
        if (mode === 0) {
            // Chuyen tu dang 29/06/2020 thanh 2020-06-29
            tmp = date.split("/");
            return (tmp[2] + "-" + tmp[1] + "-" + tmp[0]);
        }
        else if (mode === 1) {
            // Chuyen tu dang 2020-06-29 thanh 29/06/2020
            tmp = date.split("-");
            return (tmp[2] + "/" + tmp[1] + "/" + tmp[0]);
        }
    }

    return (
        isLoading ? <ActivityIndicator style={styles.loading} size={100} color='#191970'/> :
        <ScrollView style={styles.container}>
            <View style={styles.avatarContainer}>
                <Image style={{width: 80, height: 80, margin: 10}} source={accountImg}/>
                <Text style={{fontSize: 18}}>Ảnh đại diện</Text>
                <Text style={{fontSize: 18, color: 'blue', marginLeft: 100}}>Thay đổi</Text>
            </View>
            <View style={styles.infoContainer}>

                <Text style={styles.label}>Họ tên</Text>
                <TextInput style={styles.txtInfo} value={user.name} editable={editableInfo}/>
                <View style={styles.line}></View>
                
                <Text style={styles.label}>Giới tính</Text>
                <Picker
                    selectedValue={gender}
                    style={{width: 120, height: 30}}
                    itemStyle={{fontSize: 18, fontWeight:'bold', color: 'blue'}}
                    onValueChange={(itemValue, itemIndex) =>
                        {
                            setGender(itemValue);
                        }
                    }
                >
                    <Picker.Item label="Nam" value="Nam" />
                    <Picker.Item label="Nữ" value="Nữ" />
                    <Picker.Item label="Khác" value="Khác" />
                </Picker>
                <View style={styles.line}></View>

                <Text style={styles.label}>Ngày sinh</Text>
                <TextInput style={styles.txtInfo} value={changeDateFormat(user.dateOfBirth, 1)} editable={editableInfo}/>
                <View style={styles.line}></View>

                <Text style={styles.label}>Địa chỉ</Text>
                <TextInput style={styles.txtInfo} value={user.address} editable={editableInfo}/>
                <View style={styles.line}></View>

                <Text style={styles.label}>Số điện thoại</Text>
                <TextInput style={styles.txtInfo} value={user.username} editable={editableInfo}/>
                <View style={styles.line}></View>

                <Text style={styles.label}>Email</Text>
                <TextInput style={styles.txtInfo} value={user.email} editable={editableInfo}/>
                <View style={styles.line}></View>

                <Text onPress={() => setEditableInfo(true)}>Sửa</Text>
            </View>
        </ScrollView>
    );
}