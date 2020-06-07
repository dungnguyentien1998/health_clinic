import React from 'react';
import {
    StyleSheet,
    Dimensions,
} from 'react-native';

const screenHeight = Dimensions.get('window').height;

const styles = StyleSheet.create({
    container: {
        flex:1
        //height: Math.round(screenHeight*0.8),
    }
});

export default styles;