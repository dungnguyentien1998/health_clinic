import React from 'react';
import FontAwesome from 'react-native-vector-icons/FontAwesome';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { createStackNavigator } from '@react-navigation/stack';
import Home from './home';
import Controller from './controller';
import ServiceController from './servicecontroller';
import AppointmentController from './appointmentcontroller';
import PersonalInformation from './personalinformation';
import ServiceDetail from './servicedetail';
import AddService from './addservice';

const Tab = createBottomTabNavigator();
const HomeStack = createStackNavigator(); 
const ControllerStack = createStackNavigator();
const AccountStack = createStackNavigator();

function HomeScreen({route, navigation}) {
    const {userId, authorization} = route.params;
    return (
        <HomeStack.Navigator>
            <HomeStack.Screen name="Home" component={Home} options={{title: "Trang chủ"}} 
                initialParams={{userId: userId, authorization: authorization}}/>
        </HomeStack.Navigator>
    );
}

function ControllerScreen({route, navigation}) {
    const {userId, authorization} = route.params;
    return (
        <ControllerStack.Navigator>
            <ControllerStack.Screen name="Controller" component={Controller} options={{title: "Công cụ quản lý"}} 
                initialParams={{userId: userId, authorization: authorization}}/>
            <ControllerStack.Screen name="ServiceController" component={ServiceController} options={{title: "Dịch vụ phòng khám"}}/>
            <ControllerStack.Screen name="ServiceDetail" component={ServiceDetail} options={{title: "Chi tiết dịch vụ"}}/>
            <ControllerStack.Screen name="AddService" component={AddService} options={{title: "Thêm dịch vụ"}}/>
            <ControllerStack.Screen name="AppointmentController" component={AppointmentController} options={{title: "Quản lý lịch hẹn"}}/>
        </ControllerStack.Navigator>
    );
}

function AccountScreen({route, navigation}) {
    const {userId, authorization} = route.params;
    return (
        <AccountStack.Navigator>
            <AccountStack.Screen name="PersonalInformation" component={PersonalInformation} options={{title: "Thông tin cá nhân"}}
                initialParams={{userId: userId, authorization: authorization}}/>
        </AccountStack.Navigator>
    );
}

export default function AdminTabNavigator({route, navigation}) {
    const {userId, authorization} = route.params;
    return (
        <Tab.Navigator
            screenOptions={({ route }) => ({
                tabBarIcon: ({ focused, color, size }) => {
                    let iconName;
                    if (route.name === 'Trang chủ') {
                        iconName = 'home';
                    } else if (route.name === 'Công cụ quản lý') {
                        iconName = 'cogs';
                    } else if (route.name === 'Tài khoản') {
                        iconName = 'user-circle';
                    }
                    return <FontAwesome name={iconName} size={size} color={color} />;
                },
            })}
            tabBarOptions={{
                activeTintColor: '#191970',
                inactiveTintColor: 'gray',
                keyboardHidesTabBar: true,
            }}
        >
            <Tab.Screen name="Trang chủ" component={HomeScreen} initialParams={{userId: userId, authorization: authorization}}/>
            <Tab.Screen name="Công cụ quản lý" component={ControllerScreen} initialParams={{userId: userId, authorization: authorization}}/>
            <Tab.Screen name="Tài khoản" component={AccountScreen} initialParams={{userId: userId, authorization: authorization}}/>
        </Tab.Navigator>
    );
}