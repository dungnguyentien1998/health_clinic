import 'react-native-gesture-handler';
import React, {useState} from 'react';
import {
    Image,
    Text,
    View,
    TextInput,
    TouchableOpacity,
    ActivityIndicator,
    ScrollView,
    Alert
} from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import ClientTabNavigator from './src/component/client/tabnavigator';
import AdminTabNavigator from './src/component/admin/admintabnavigator';
import AsyncStorage from '@react-native-community/async-storage';
import {styles as signinstyles} from './src/style/signin';
import {styles as signupstyles} from './src/style/signup';
import styles from './src/style/home';

const AuthContext = React.createContext();
const Stack = createStackNavigator();

// -------------- Man hinh dang nhap --------------

function SignIn({navigation}) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [isLoading, setLoading] = useState(false);

    const {signIn} = React.useContext(AuthContext);
    logo = require('./src/icon/login/logo.png');

    return ( 
        <View style={signinstyles.container} accessible={false}>
            {isLoading &&
            <View style={[signinstyles.loading, {backgroundColor: 'rgba(192,192,192,0.7)'}]}></View>
            }
            {isLoading &&
            <View style={signinstyles.loading}>
                <ActivityIndicator size={100} color='#191970'/>
            </View>
            }

            <Image style={signinstyles.logo} source={logo}></Image>
            <TextInput
                onChangeText={(text) => setUsername(text)}
                underlineColorAndroid={'#191970'}
                style={signinstyles.textInput}
                placeholder={'Số điện thoại'}
                editable={!isLoading}
            />
            <TextInput
                onChangeText={(text) => setPassword(text)}
                underlineColorAndroid={'#191970'}
                style={signinstyles.textInput}
                placeholder={'Mật khẩu'}
                secureTextEntry={true}
                editable={!isLoading}
            />
            <TouchableOpacity
                onPress={async () => {
                setLoading(true);
                result = await signIn({ username, password });
                setLoading(false);
                if (result === 1)
                    Alert.alert(
                        "Thông báo",
                        "Lỗi kết nối!",
                        [
                            {
                                text: "OK",
                                style: "cancel"
                            }
                        ]
                    );
                else if (result === 0)
                    Alert.alert(
                        "Thông báo",
                        "Tên đăng nhập hoặc mật khẩu không đúng!",
                        [
                            {
                                text: "OK",
                                style: "cancel"
                            }
                        ]
                    );
                }} 
                style={signinstyles.btnLogin}
                disabled={isLoading}
            >
                <Text style={signinstyles.text}>ĐĂNG NHẬP</Text>
            </TouchableOpacity>

            <View style={signinstyles.containerOR}>
                <View style={signinstyles.line}></View>
                <Text style={signinstyles.textOR}>HOẶC</Text>
                <View style={signinstyles.line}></View>
            </View>
            <TouchableOpacity
                style={signinstyles.btnSignUp}
                onPress={() => navigation.navigate('SignUp')}
                disabled={isLoading}
            >
                <Text style={signinstyles.text}>ĐĂNG KÝ TÀI KHOẢN</Text>
            </TouchableOpacity>
        </View>
    );
}

// -------------- Man hinh dang ky --------------

function SignUp({navigation}) {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [phone, setPhone] = useState('');
    const [password, setPassword] = useState('');
    const [submitPwd, setSubmitPwd] = useState('');

    return (
        <ScrollView contentContainerStyle={signupstyles.container}>
            <Text style={signupstyles.title}>Đăng ký</Text>
            <TextInput
                onChangeText={(text) => setName(text)}
                underlineColorAndroid={'#191970'}
                style={signupstyles.textInput}
                placeholder={'Họ và tên'}
            />
            <TextInput
                onChangeText={(text) => setEmail(text)}
                underlineColorAndroid={'#191970'}
                style={signupstyles.textInput}
                placeholder={'Email'}
            />
            <TextInput
                onChangeText={(text) => setPhone(text)}
                underlineColorAndroid={'#191970'}
                style={signupstyles.textInput}
                placeholder={'Số điện thoại'}
            />
            <TextInput
                onChangeText={(text) => setPassword(text)}
                underlineColorAndroid={'#191970'}
                style={signupstyles.textInput}
                placeholder={'Mật khẩu'}
                secureTextEntry={true}
            />
            <TextInput
                onChangeText={(text) => setSubmitPwd(text)}
                underlineColorAndroid={'#191970'}
                style={signupstyles.textInput}
                placeholder={'Xác nhận mật khẩu'}
                secureTextEntry={true}
            />
            <TouchableOpacity
                style={signupstyles.btnSignup}
            >
                <Text style={signupstyles.text}>ĐĂNG KÝ</Text>
            </TouchableOpacity>
        </ScrollView>
    );
}




export default function App({ navigation }) {
    const [state, dispatch] = React.useReducer(
        (prevState, action) => {
            switch (action.type) {
                case 'RESTORE_TOKEN':
                    return {
                        ...prevState,
                        userToken: action.token,
                    };
                case 'SIGN_IN':
                    return {
                        ...prevState,
                        isSignout: false,
                        userToken: action.token,
                        isAdmin: action.isAdmin
                    };
                case 'SIGN_OUT':
                    return {
                        ...prevState,
                        isSignout: true,
                        userToken: null,
                    };
            }
        },
        {   
            id: '7',
            isSignout: false,
            userToken: null,
            isAdmin: false
        }
    );

    React.useEffect(() => {
        const bootstrapAsync = async () => {
            let userToken;
            try {
                userToken = await AsyncStorage.getItem('userToken');
            } catch (e) {
        
            }
            dispatch({ type: 'RESTORE_TOKEN', token: userToken });
        };

        bootstrapAsync();
    }, []);

    const authContext = React.useMemo(
        () => ({
            signIn: async data => {
                let userToken;
                let result = 0;
                try {
                    let response = await fetch('http://192.168.56.1:8080/login', {
                        method: 'POST',
                        headers: {
                            Accept: '*/*',
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({
                            username: data.username,
                            password: data.password
                        })
                    })
                    let json = await response.json();
                    userToken = await json.accessToken === undefined ? null: json.accessToken;
                    if (userToken != null) result = 2;
                } catch (e) {
                    userToken = null;
                    result = 1;
                }
                dispatch({ type: 'SIGN_IN', token: userToken, isAdmin: (data.username === "0111111111") });
                return result;
            },

            signOut: () => dispatch({ type: 'SIGN_OUT' }),

            signUp: async data => {
                dispatch({ type: 'SIGN_IN', token: 'dummy-auth-token' });
            },
        }),[]
    );

    return (
        <AuthContext.Provider value={authContext}>
            <NavigationContainer>
                <Stack.Navigator headerMode="none">
            
                    {state.userToken === null ? (
                    <>
                        <Stack.Screen 
                            name="SignIn" component={SignIn}
                            options = {{title: "Đăng nhập"}}
                        />
                        <Stack.Screen 
                            name="SignUp" component={SignUp}
                            options = {{title: "Đăng ký"}}
                        />
                    </>
                    ) : (
                    
                    state.isAdmin ?
                    <Stack.Screen 
                        name="AdminTabNavigator" component={AdminTabNavigator}
                        options = {{title: "Trang chủ"}}
                        initialParams={{userId: state.id}}
                    />
                    :
                    <Stack.Screen 
                        name="ClientTabNavigator" component={ClientTabNavigator}
                        options = {{title: "Trang chủ"}}
                        initialParams={{userId: state.id}}
                    />
                    )}
                </Stack.Navigator>
            </NavigationContainer>
        </AuthContext.Provider>
    );
}