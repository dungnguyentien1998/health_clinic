import React from 'react';
import {StyleSheet} from 'react-native';

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center'
    },
    service: {
        flex: 1,
        flexDirection: 'row',
        borderRadius: 15,
        marginHorizontal: 30,
        marginVertical: 15,
        backgroundColor: '#deb887',
        alignItems: 'center',
        justifyContent: 'flex-start'
    },
    icon:{
        width: 50,
        height: 50,
        marginHorizontal: 15,
    },
    serviceName: {
        fontWeight: 'bold',
        fontSize: 25,
    },
    textContainer: {
        flex: 0.3,
        flexDirection: 'row',
        alignItems: 'center',
        marginHorizontal: 10
    },
    line: {
        flex: 1,
        height: 1,
        backgroundColor: 'gray',
    },
    text: {
        fontSize: 16,
        fontWeight: "900",
        marginHorizontal: 10,
    },
    btnContainer: {
        flex: 0.7,
        justifyContent: 'center',
        alignItems: 'center'
    },
    btnSurvey: {
        padding: 10,
        backgroundColor: '#191970',
        textAlign: 'center',
        textAlignVertical: 'center',
        borderRadius: 10,
        color: 'white',
        fontSize: 16,
    }
});

export default styles;