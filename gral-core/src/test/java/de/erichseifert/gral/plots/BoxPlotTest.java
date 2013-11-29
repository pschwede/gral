/*
 * GRAL: GRAphing Library for Java(R)
 *
 * (C) Copyright 2009-2013 Erich Seifert <dev[at]erichseifert.de>,
 * Michael Seifert <michael[at]erichseifert.de>
 *
 * This file is part of GRAL.
 *
 * GRAL is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GRAL is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GRAL.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.erichseifert.gral.plots;

import static de.erichseifert.gral.TestUtils.assertNotEmpty;
import static de.erichseifert.gral.TestUtils.createTestImage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.erichseifert.gral.TestUtils;
import de.erichseifert.gral.data.DataSource;
import de.erichseifert.gral.data.DummyData;
import de.erichseifert.gral.data.EnumeratedData;
import de.erichseifert.gral.graphics.DrawingContext;
import de.erichseifert.gral.plots.BoxPlot.BoxWhiskerRenderer;
import de.erichseifert.gral.plots.points.PointRenderer;

public class BoxPlotTest {
	private static final double DELTA = 1e-7;

	private DataSource data;
	private MockBoxPlot plot;

	private static final class MockBoxPlot extends BoxPlot {
		/** Version id for serialization. */
		private static final long serialVersionUID = 4497026503195874443L;

		public boolean isDrawn;

		public MockBoxPlot(DataSource data) {
			super(data);
		}

		@Override
		public void draw(DrawingContext context) {
			super.draw(context);
			isDrawn = true;
		}
	}

	@Before
	public void setUp() {
		data = new EnumeratedData(new DummyData(5, 3, 1.0));
		plot = new MockBoxPlot(data);
	}

	@Test
	public void testDraw() {
		plot.getAxis(BarPlot.AXIS_X).setRange(-1.0, 3.0);
		plot.getAxis(BarPlot.AXIS_Y).setRange(-1.0, 2.0);
		BufferedImage image = createTestImage();
		plot.setBounds(0.0, 0.0, image.getWidth(), image.getHeight());
		DrawingContext context = new DrawingContext((Graphics2D) image.getGraphics());
		plot.draw(context);
		assertTrue(plot.isDrawn);
		assertNotEmpty(image);
	}

	@Test
	public void testAddRemoveData() {
		plot.remove(data);
		assertEquals(0, plot.getData().size());
		plot.add(data);
		assertEquals(1, plot.getData().size());
		try {
			plot.add(data);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testSerialization() throws IOException, ClassNotFoundException {
		BoxPlot original = plot;
		BoxPlot deserialized = TestUtils.serializeAndDeserialize(original);

		TestUtils.assertSettings(original, deserialized);

		assertEquals(original.getBackground(), deserialized.getBackground());
		assertEquals(original.getBorder(), deserialized.getBorder());
		assertEquals(original.getColor(), deserialized.getColor());
		assertEquals(original.isLegendVisible(), deserialized.isLegendVisible());

		List<DataSource> dataSourcesOriginal = original.getData();
		List<DataSource> dataSourcesDeserialized = deserialized.getData();
		assertEquals(dataSourcesOriginal.size(), dataSourcesDeserialized.size());
		for (int index = 0; index < dataSourcesOriginal.size(); index++) {
			PointRenderer pointRendererOriginal = original.getPointRenderer(
							dataSourcesOriginal.get(index));
			PointRenderer pointRendererDeserialized = deserialized.getPointRenderer(
							dataSourcesDeserialized.get(index));
			testPointRendererSerialization(pointRendererOriginal, pointRendererDeserialized);
		}
    }

	private static void testPointRendererSerialization(
			PointRenderer originalRenderer, PointRenderer deserializedRenderer) {
		BoxWhiskerRenderer original = (BoxWhiskerRenderer) originalRenderer;
		BoxWhiskerRenderer deserialized = (BoxWhiskerRenderer) deserializedRenderer;
		assertEquals(original.getColumnPosition(), deserialized.getColumnPosition());
		assertEquals(original.getColumnBarCenter(), deserialized.getColumnBarCenter());
		assertEquals(original.getColumnBarBottom(), deserialized.getColumnBarBottom());
		assertEquals(original.getColumnBoxBottom(), deserialized.getColumnBoxBottom());
		assertEquals(original.getColumnBoxTop(), deserialized.getColumnBoxTop());
		assertEquals(original.getColumnBarTop(), deserialized.getColumnBarTop());
		assertEquals(original.getBoxWidth(), deserialized.getBoxWidth(), DELTA);
		assertEquals(original.getBoxBackground(), deserialized.getBoxBackground());
		assertEquals(original.getBoxColor(), deserialized.getBoxColor());
		assertEquals(original.getBoxBorder(), deserialized.getBoxBorder());
		assertEquals(original.getWhiskerColor(), deserialized.getWhiskerColor());
		assertEquals(original.getWhiskerStroke(), deserialized.getWhiskerStroke());
		assertEquals(original.getBarWidth(), deserialized.getBarWidth(), DELTA);
		assertEquals(original.getBarCenterColor(), deserialized.getBarCenterColor());
		assertEquals(original.getBarCenterStroke(), deserialized.getBarCenterStroke());
	}
}
