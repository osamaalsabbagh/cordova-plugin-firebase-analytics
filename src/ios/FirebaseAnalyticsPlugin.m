#import "FirebaseAnalyticsPlugin.h"
@import FirebaseAnalytics;


@implementation FirebaseAnalyticsPlugin

- (void)pluginInitialize {
    NSLog(@"Starting Firebase Analytics plugin");

    if(![FIRApp defaultApp]) {
        [FIRApp configure];
    }
}

- (void)logEvent:(CDVInvokedUrlCommand *)command {
    [self.commandDelegate runInBackground:^{
        NSString* name = [command.arguments objectAtIndex:0];
        NSDictionary* parameters = [command.arguments objectAtIndex:1];
        
        [FIRAnalytics logEventWithName:name parameters:parameters];
        
        CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

- (void)setUserId:(CDVInvokedUrlCommand *)command {
    [self.commandDelegate runInBackground:^{
        NSString* id = [command.arguments objectAtIndex:0];
        
        [FIRAnalytics setUserID:id];
        
        CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

- (void)setUserProperty:(CDVInvokedUrlCommand *)command {
    [self.commandDelegate runInBackground:^{
        NSString* name = [command.arguments objectAtIndex:0];
        NSString* value = [command.arguments objectAtIndex:1];
        
        [FIRAnalytics setUserPropertyString:value forName:name];
        
        CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

- (void)setEnabled:(CDVInvokedUrlCommand *)command {
	[self.commandDelegate runInBackground:^{
		NSString* enabledStr = [command.arguments objectAtIndex:0];
		BOOL enabled = [enabledStr isEqualToString:@"true"];
	
		[[FIRAnalyticsConfiguration sharedInstance] setAnalyticsCollectionEnabled:enabled];
	
		CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
		[self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
	}];
}

- (void)setCurrentScreen:(CDVInvokedUrlCommand *)command {
	[self.commandDelegate runInBackground:^{
		NSString* name = [command.arguments objectAtIndex:0];
	
		[FIRAnalytics setScreenName:name screenClass:nil];
	
		CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
		[self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

@end
