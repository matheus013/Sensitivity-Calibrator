package projects.Flooding.Timers;

import jsensor.runtime.Jsensor;

import java.awt.Point;

import environment.pattern.Singleton;
import jsensor.nodes.Node;
import jsensor.nodes.events.TimerEvent;
import jsensor.utils.GenerateFilesOmnet;
import jsensor.utils.Position;
import projects.Flooding.Messages.FloodingMessage;

/**
 *
 * @author Danniel & Matheus
 */
public class FloodingTimer extends TimerEvent {

	@Override
	public void fire() {

		Node destination = this.node.getRandomNode("FloodingNode");
		while (true) {
			if (destination == null) {
				destination = this.node.getRandomNode("FloodingNode");
				continue;
			}

			if (this.node == destination) {
				destination = this.node.getRandomNode("FloodingNode");
				continue;
			}
			break;
		}

		FloodingMessage message = new FloodingMessage(this.node, destination, 0, "" + this.node.getID(),
				this.node.getChunk());
		Position p = this.node.getPosition();
		String messagetext = String.valueOf(Singleton.getInstance().getTemperature(this.node));

		message.setMsg(messagetext);
		Jsensor.log("time: " + Jsensor.currentTime + "\t sensorID: " + this.node.getID() + "\t sendTo: "
				+ destination.getID());

		GenerateFilesOmnet.addStartNode(this.node.getID(), destination.getID(), Jsensor.currentTime);
		this.node.multicast(message);
	}
}
