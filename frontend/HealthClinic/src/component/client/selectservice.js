import React, {useState, useEffect, createFactory} from 'react';
import {
    Image,
    Text,
    View,
    ScrollView,
    TouchableOpacity,
    ActivityIndicator,
    Alert,
} from 'react-native';
import styles from '../../style/selectservice';

export default function SelectService({route, navigation}) {
    const {userId} = route.params;
    const eyeIcon = require('../../icon/selectservice/eye.png');
    const earIcon = require('../../icon/selectservice/ear.png');
    const teethIcon = require('../../icon/selectservice/teeth.png');
    const skinIcon = require('../../icon/selectservice/skin.png');
    const [services, setServices] = useState([]);
    const [isLoading, setLoading] = useState(true);

    useEffect(() => {
        fetch('http://192.168.56.1:8080/clinicservices')
            .then((response) => response.json())
            .then((json) => setServices(json))
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
    
    return (
        <View style={styles.container}>
            {isLoading ? <ActivityIndicator size={100} color='#191970'/> : (
                <View style={styles.container}>
                    <TouchableOpacity
                        style={styles.service} 
                        onPress={() => navigation.navigate('ServiceDetail', {service: services[0], userId: userId})}
                    >
                        <Image style={styles.icon} source={eyeIcon}/>
                        <Text style={styles.serviceName}>{(services[0]).name}</Text>
                    </TouchableOpacity>

                    <TouchableOpacity 
                        style={styles.service}
                        onPress={() => navigation.navigate('ServiceDetail', {service: services[1], userId: userId})}
                    >
                        <Image style={styles.icon} source={earIcon}/>
                        <Text style={styles.serviceName}>{(services[1]).name}</Text>
                    </TouchableOpacity>

                    <TouchableOpacity 
                        style={styles.service}
                        onPress={() => navigation.navigate('ServiceDetail', {service: services[2], userId: userId})}
                    >
                        <Image style={styles.icon} source={teethIcon}/>
                        <Text style={styles.serviceName}>{(services[2]).name}</Text>
                    </TouchableOpacity>

                    <TouchableOpacity
                        style={styles.service} 
                        onPress={() => navigation.navigate('ServiceDetail', {service: services[3], userId: userId})}
                    >
                        <Image style={styles.icon} source={skinIcon}/>
                        <Text style={styles.serviceName}>{(services[3]).name}</Text>
                    </TouchableOpacity>

                    <View style={styles.textContainer}>
                        <View style={styles.line}></View>
                        <Text style={styles.text}>BẠN CHƯA CHỌN ĐƯỢC DỊCH VỤ?</Text>
                        <View style={styles.line}></View>
                    </View>

                    <View style={styles.btnContainer}>
                        <TouchableOpacity>
                            <Text style={styles.btnSurvey}>KHẢO SÁT SỨC KHỎE</Text>
                        </TouchableOpacity>
                    </View>
                </View>
            )}
        </View>
    );
}