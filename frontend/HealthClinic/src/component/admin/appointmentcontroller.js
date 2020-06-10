import React, {useState} from 'react';
import {
    Text,
    View,
    TouchableOpacity,
    FlatList,
    ActivityIndicator,
    Alert,
} from 'react-native';
import styles from '../../style/appointmentcontroller';
import AntDesign from 'react-native-vector-icons/AntDesign';
import DateTimePicker from '@react-native-community/datetimepicker';
import FontAwesome5 from 'react-native-vector-icons/FontAwesome5';

export default function AppointmentController({route, navigation}) {
    const {userId, authorization} = route.params;
    const [datetime, setDatetime] = useState(new Date());
    const [showDatePicker, setShowDatePicker] = useState(false);
    const [date, setDate] = useState(showDate(datetime));
    const [isLoading, setLoading] = useState(true);
    const [appts, setAppts] = useState([]); 
    const [noAppt, setNoAppt] = useState(false);

    React.useEffect(() => {
        getAppt();
    }, []);

    function getAppt() {
        setLoading(true);
        fetch('http://192.168.56.1:8080/appointments', {
            method: 'GET',
            headers: {
                Accept: '*/*',
                'Content-Type': 'application/json',
                Authorization: authorization
            }
        })
            .then((response) => response.status === 204 ? [] : response.json())
            .then((json) => {
                setAppts(json);
                if (json.length === 0) 
                    setNoAppt(true);
                else
                    setNoAppt(false);
                return json;
            })
            .catch((error) => {
                setNoAppt(true);
                Alert.alert(
                    "Thông báo",
                    "Lỗi kết nối",
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
    function changeTimeFormat(time) {
        if (time.length === 8)
            return time.substr(0,5);
        else if (time.length === 5)
            return (time + ":00");
    }

    function showDate(selectTime) {
        d = selectTime.getDate();
        m = selectTime.getMonth() + 1;
        y = selectTime.getFullYear();
        tmp = ((d > 9) ? d : '0' + d) + '/' + ((m > 9) ? m : '0' + m) + '/' + y;
        return tmp;
    }

    const onChange = (event, selectedDate) => {
        const currentDate = selectedDate || datetime;
        setShowDatePicker(false);
        setDatetime(currentDate);
        tmp = showDate(currentDate);
        setDate(tmp);
    };

    return (
        <View style={styles.container}>
            <View style={styles.dateBar}>
                <TouchableOpacity>
                    <AntDesign
                        name='caretleft' size={35} color='#191970'
                        onPress={() =>{
                            datetime.setDate(datetime.getDate() - 1);
                            setDate(showDate(datetime));
                        }}
                    />
                </TouchableOpacity>

                <TouchableOpacity
                    onPress={() => setShowDatePicker(true)}
                >
                    <Text  style={styles.dateBox}>{date}</Text>
                </TouchableOpacity>
                
                <TouchableOpacity>
                    <AntDesign
                        name='caretright' size={35} color='#191970' 
                        onPress={() =>{
                            datetime.setDate(datetime.getDate() + 1);
                            setDate(showDate(datetime));
                        }}
                    />
                </TouchableOpacity>
            </View>

            {showDatePicker && (
                <DateTimePicker
                    testID="dateTimePicker"
                    timeZoneOffsetInMinutes={0}
                    value={datetime}
                    mode={'date'}
                    is24Hour={true}
                    display="default"
                    onChange={onChange}
                />
            )}     

            <View style={styles.apptContainer}>
                {isLoading ? <ActivityIndicator size={100} color='#191970'/> :
                    (noAppt ? <Text style={styles.content}>Không có lịch hẹn nào.</Text> :
                        <FlatList
                            style={{marginVertical: 5}}
                            data={appts}
                            renderItem={({item}) => (
                                <TouchableOpacity style={styles.item}>
                                    <View style={{flexDirection: 'row'}}>
                                        <View style={styles.itemRow}>
                                            <FontAwesome5 name={'medkit'} color='#191970' size={25} solid/>
                                            <Text style={styles.txtList}>{item.clinicServiceName}</Text>
                                        </View>
                                        <View style={styles.itemRow}>
                                            <FontAwesome5 name={'clinic-medical'} color='#191970' size={25} solid/>
                                            <Text style={styles.txtList}>{item.calendarRoom}</Text>
                                        </View>
                                    </View>

                                    <View style={{flexDirection: 'row', marginTop: 5}}>
                                            <View style={styles.itemRow}>
                                                <FontAwesome5 name={'clock'} color='#191970' size={25} solid/>
                                                <Text style={styles.txtList}>{changeTimeFormat(item.calendarTimeStart)}</Text>
                                            </View>

                                            <View style={styles.itemRow}>
                                                <FontAwesome5 style={{marginHorizontal: 3}} name={'calendar-alt'} color='#191970' size={25} solid/>    
                                                <Text style={styles.txtList}>{changeDateFormat(item.calendarDate, 1)}</Text>
                                            </View>
                                        </View>
                                </TouchableOpacity>
                            )}
                            keyExtractor={item => item.id.toString()}
                        />
                    )
                }
            </View>

            <View style={styles.btnContainer}>
                <TouchableOpacity 
                    onPress={() => {
                    }} 
                    style={styles.btnFind}
                >
                    <FontAwesome5 name={'search'} size={28} color='white'/>
                </TouchableOpacity>
            </View>
        </View>
    );
}