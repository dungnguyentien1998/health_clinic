import React from 'react';
import FontAwesome from 'react-native-vector-icons/FontAwesome';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { createStackNavigator } from '@react-navigation/stack';
import Home from './home';
import Controller from './controller';
import ServiceController from './servicecontroller';
import PersonalInformation from './personalinformation';

const Tab = createBottomTabNavigator();
const HomeStack = createStackNavigator(); 
const ControllerStack = createStackNavigator();
const AccountStack = createStackNavigator();

function HomeScreen() {
    return (
        <HomeStack.Navigator>
            <HomeStack.Screen name="Home" component={Home} options={{title: "Trang chủ"}}/>
        </HomeStack.Navigator>
    );
}

function ControllerScreen() {
    return (
        <ControllerStack.Navigator>
            <ControllerStack.Screen name="Controller" component={Controller} options={{title: "Công cụ quản lý"}}/>
            <ControllerStack.Screen name="ServiceController" component={ServiceController} options={{title: "Dịch vụ phòng khám"}}/>
        </ControllerStack.Navigator>
    );
}

function AccountScreen() {
  return (
    <AccountStack.Navigator>
      <AccountStack.Screen name="PersonalInformation" component={PersonalInformation} options={{title: "Thông tin cá nhân"}}/>
    </AccountStack.Navigator>
  );
}

export default function AdminTabNavigator({route, navigation}) {
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
        <Tab.Screen name="Trang chủ" component={HomeScreen}/>
        <Tab.Screen name="Công cụ quản lý" component={ControllerScreen}/>
        <Tab.Screen name="Tài khoản" component={AccountScreen}/>
      </Tab.Navigator>
  );
}