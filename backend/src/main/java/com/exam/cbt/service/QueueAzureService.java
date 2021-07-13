package com.exam.cbt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.exam.cbt.pojo.CandidateResponseUI;
import com.exam.cbt.service.impl.CandidateSubmissionHistoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.queue.CloudQueue;
import com.microsoft.azure.storage.queue.CloudQueueClient;
import com.microsoft.azure.storage.queue.CloudQueueMessage;
import com.azure.core.util.*;
import com.azure.storage.queue.*;
import com.azure.storage.queue.models.*;

@Service
public class QueueAzureService {

	@Autowired
	SubmitExamService submitExamService;

	@Autowired
	QueueProcessingStatusService queueProcessingStatusService;

	@Autowired
	CandidateSubmissionHistoryServiceImpl candidateSubmissionHistoryServiceImpl;

	@Value("${mdms.i001.2001.queue}")
	private String configuredQueue;

	@Value("${azure.storage.ConnectionString}")
	private String azureConnectionString;
	
	@Value("${numberofqueueMessages}")
	private int numberofqueueMessages;
	
	@Value("${visibilityTimeOutInSeconds}")
	private int visibilityTimeOutInSeconds;
	
//	public static void sendMessage1() {
//
//		// create a Service Bus Sender client for the queue
//		ServiceBusSenderClient senderClient = new ServiceBusClientBuilder().connectionString(connectionString).sender()
//				.queueName(queueName).buildClient();
//
//		// send one message to the queue
//		senderClient.sendMessage(new ServiceBusMessage("Hello, World!"));
//		System.out.println("Sent a single message to the queue: " + queueName);
//	}

//	static List<ServiceBusMessage> createMessages() {
//		// create a list of messages and return it to the caller
//		ServiceBusMessage[] messages = { new ServiceBusMessage("First message"),
//				new ServiceBusMessage("Second message"), new ServiceBusMessage("Third message") };
//		return Arrays.asList(messages);
//	}

//	public static void sendMessageBatch() {
//		// create a Service Bus Sender client for the queue
//		ServiceBusSenderClient senderClient = new ServiceBusClientBuilder().connectionString(connectionString).sender()
//				.queueName(queueName).buildClient();
//
//		// Creates an ServiceBusMessageBatch where the ServiceBus.
//		ServiceBusMessageBatch messageBatch = senderClient.createMessageBatch();
//
//		// create a list of messages
//		List<ServiceBusMessage> listOfMessages = createMessages();
//
//		// We try to add as many messages as a batch can fit based on the maximum size
//		// and send to Service Bus when
//		// the batch can hold no more messages. Create a new batch for next set of
//		// messages and repeat until all
//		// messages are sent.
//		for (ServiceBusMessage message : listOfMessages) {
//			if (messageBatch.tryAddMessage(message)) {
//				continue;
//			}
//
//			// The batch is full, so we create a new batch and send the batch.
//			senderClient.sendMessages(messageBatch);
//			System.out.println("Sent a batch of messages to the queue: " + queueName);
//
//			// create a new batch
//			messageBatch = senderClient.createMessageBatch();
//
//			// Add that message that we couldn't before.
//			if (!messageBatch.tryAddMessage(message)) {
//				System.err.printf("Message is too large for an empty batch. Skipping. Max size: %s.",
//						messageBatch.getMaxSizeInBytes());
//			}
//		}
//
//		if (messageBatch.getCount() > 0) {
//			senderClient.sendMessages(messageBatch);
//			System.out.println("Sent a batch of messages to the queue: " + queueName);
//		}
//
//		// close the client
//		senderClient.close();
//	}
	// service bus queue
//	public void receiveMessages() throws InterruptedException {
//		CountDownLatch countdownLatch = new CountDownLatch(1);
//
//		// Create an instance of the processor through the ServiceBusClientBuilder
//		ServiceBusProcessorClient processorClient = new ServiceBusClientBuilder().connectionString(connectionString)
//				.processor().queueName(queueName).processMessage(QueueAzureService::processMessage)
//				.processError(context -> processError(context, countdownLatch)).buildProcessorClient();
//
//		System.out.println("Starting the processor");
//		processorClient.start();
//
//		//TimeUnit.SECONDS.sleep(10);
//		System.out.println("Stopping and closing the processor");
//		processorClient.close();
//	}

//	private static void processError(ServiceBusErrorContext context, CountDownLatch countdownLatch) {
//		System.out.printf("Error when receiving messages from namespace: '%s'. Entity: '%s'%n",
//				context.getFullyQualifiedNamespace(), context.getEntityPath());
//
//		if (!(context.getException() instanceof ServiceBusException)) {
//			System.out.printf("Non-ServiceBusException occurred: %s%n", context.getException());
//			return;
//		}
//
//		ServiceBusException exception = (ServiceBusException) context.getException();
//		ServiceBusFailureReason reason = exception.getReason();
//
//		if (reason == ServiceBusFailureReason.MESSAGING_ENTITY_DISABLED
//				|| reason == ServiceBusFailureReason.MESSAGING_ENTITY_NOT_FOUND
//				|| reason == ServiceBusFailureReason.UNAUTHORIZED) {
//			System.out.printf("An unrecoverable error occurred. Stopping processing with reason %s: %s%n", reason,
//					exception.getMessage());
//
//			countdownLatch.countDown();
//		} else if (reason == ServiceBusFailureReason.MESSAGE_LOCK_LOST) {
//			System.out.printf("Message lock lost for message: %s%n", context.getException());
//		} else if (reason == ServiceBusFailureReason.SERVICE_BUSY) {
//			try {
//				// Choosing an arbitrary amount of time to wait until trying again.
//				TimeUnit.SECONDS.sleep(1);
//			} catch (InterruptedException e) {
//				System.err.println("Unable to sleep for period of time");
//			}
//		} else {
//			System.out.printf("Error source %s, reason %s, message: %s%n", context.getErrorSource(), reason,
//					context.getException());
//		}
//	}

//	private static void processMessage(ServiceBusReceivedMessageContext context) {
//		ServiceBusReceivedMessage message = context.getMessage();
//		System.out.printf("Processing message. Session: %s, Sequence #: %s. Contents: %s%n", message.getMessageId(),
//				message.getSequenceNumber(), message.getBody());
//	}

	public void sendMessage(String str) throws StorageException {

		try {
			// Retrieve storage account from connection-string.
			CloudStorageAccount storageAccount = CloudStorageAccount
					.parse(azureConnectionString);

			// Create the queue client.
			CloudQueueClient queueClient = storageAccount.createCloudQueueClient();

			// Retrieve a reference to a queue.
			CloudQueue queue = queueClient.getQueueReference(configuredQueue);

			// Create the queue if it doesn't already exist.
			// queue.createIfNotExists();

			// Create a message and add it to the queue.
			CloudQueueMessage message = new CloudQueueMessage(str);
			queue.addMessage(message);
		} catch (Exception e) {
			// Output the stack trace.
			e.printStackTrace();
		}

	}

	public void readMessage() throws StorageException {

		try {

			
			// Retrieve storage account from connection-string.
			CloudStorageAccount storageAccount = CloudStorageAccount.parse(azureConnectionString);

			// Create the queue client.
			CloudQueueClient queueClient = storageAccount.createCloudQueueClient();

			// Retrieve a reference to a queue.
			CloudQueue queue = queueClient.getQueueReference(configuredQueue);
			
//			QueueClient queueClientNew = new QueueClientBuilder()
//                    .connectionString(azureConnectionString)
//                    .queueName("cbtmdmsi0012021queue")
//                    .buildClient();
			
			// Create the queue if it doesn't already exist.
			// queue.createIfNotExists();

			// Create a message and add it to the queue.
			// Retrieve the first visible message in the queue.
			// CloudQueueMessage retrievedMessage = queue.retrieveMessage();
			// Download the approximate message count from the server.
			// queue.downloadAttributes();

			// Retrieve the newly cached approximate message count.
			// long cachedMessageCount = queue.getApproximateMessageCount();
			// Retrieve 20 messages from the queue with a visibility timeout of 300 seconds.
			queueProcessingStatusService.updateQueueProcessingStatus("MDMS", "I001", 2021, "PROCESSING");
			for (CloudQueueMessage message : queue.retrieveMessages(32, visibilityTimeOutInSeconds, null, null)) {
				// Do processing for all messages in less than 5 minutes,
				// deleting each message after processing.
				queue.deleteMessage(message);
				System.out.println("Message retrieved from queue: " + message.getMessageContentAsString());
				// CloudQueueMessage resp = message;
				CandidateResponseUI resp = new ObjectMapper().readValue(message.getMessageContentAsString(),
						CandidateResponseUI.class);
				System.out.println(resp);
				candidateSubmissionHistoryServiceImpl.saveCandidateAnswerFromQueue(resp.getResp(),
						message.getMessageId());
				submitExamService.submitCandidateExam(resp,message.getMessageId());
			}
			queueProcessingStatusService.updateQueueProcessingStatus("MDMS", "I001", 2021, "COMPLETED");

//		    if (retrievedMessage != null)
//		    {
//		        // Process the message in less than 30 seconds, and then delete the message.
//		        queue.deleteMessage(retrievedMessage);
//		    }

		} catch (Exception e) {
			queueProcessingStatusService.updateQueueProcessingStatus("MDMS", "I001", 2021, "COMPLETED");
			// Output the stack trace.
			e.printStackTrace();
		}

	}

}
