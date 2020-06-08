import React, {useState} from 'react';
import {
    Text,
    View,
    ScrollView,
    TouchableOpacity,
} from 'react-native';
import styles from '../../style/servicedetail';

export default function ServiceDetail({route, navigation}) {
    const {service, userId} = route.params;
    return (
        <View style={styles.container}>
            <View style={styles.titleContainer}>
                <Text style={styles.title}>{service.name}</Text>
            </View>
            <View style={styles.desciptionContainer}>
                <ScrollView style={styles.description}>
                    <Text style={styles.txtDescription}>{service.description}</Text>
                </ScrollView>
            </View>
            
            <View style={styles.btnContainer}>
                <TouchableOpacity 
                    style={styles.button}
                    onPress={() => navigation.navigate('MakeAppointment', {service: service, userId: userId})}
                >
                    <Text style={styles.btnText}>Tạo lịch hẹn</Text>
                </TouchableOpacity>
            </View>
        </View>
    );
}