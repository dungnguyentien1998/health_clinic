import React, {useState, useEffect} from 'react';
import {
    Text, 
    View,
    FlatList,
    ActivityIndicator,
    Alert,
    TouchableOpacity
} from 'react-native';
import styles from '../../style/servicecontroller';

export default function ServiceController({route, navigation}) {
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
            {isLoading ? <ActivityIndicator size={100} color='#191970'/> : 
                <View style={styles.container}>
                    <FlatList
                        style={styles.listContainer}
                        data={services}
                        renderItem={({item}) => (
                            <TouchableOpacity
                                style={styles.item}
                            >
                                <View style={{alignItems: 'center'}}>
                                    <Text style={styles.name}>{item.name}</Text>
                                </View>
                            </TouchableOpacity>
                            )}
                        keyExtractor={item => item.id.toString()}
                    />

                    <View style={styles.btnContainer}>
                        <TouchableOpacity
                            style={styles.button}
                        >
                            <Text style={styles.btnText}>Thêm dịch vụ</Text>
                        </TouchableOpacity>
                    </View>
                </View>
            }
        </View>
    );
} 