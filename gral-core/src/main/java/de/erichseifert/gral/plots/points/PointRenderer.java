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
package de.erichseifert.gral.plots.points;

import java.awt.Font;
import java.awt.Paint;
import java.awt.Shape;
import java.text.Format;

import de.erichseifert.gral.graphics.Drawable;
import de.erichseifert.gral.plots.colors.ColorMapper;
import de.erichseifert.gral.plots.settings.Key;
import de.erichseifert.gral.plots.settings.SettingsStorage;
import de.erichseifert.gral.util.Location;

/**
 * <p>An interface providing functions for rendering points in a plot.
 * It defines methods for:</p>
 * <ul>
 *   <li>Retrieving the point of a certain row in a DataTable</li>
 *   <li>Getting and setting the points color</li>
 *   <li>Getting and setting the bounds of the points</li>
 * </ul>
 */
public interface PointRenderer extends SettingsStorage {
	/** Key for specifying a {@link Integer} value for the index of
	the column that contains the lower error value. */
	Key ERROR_COLUMN_BOTTOM = new Key("point.error.columnBottom"); //$NON-NLS-1$
	/** Key for specifying the {@link java.awt.Paint} instance to be used to
	paint the error bars. */
	Key ERROR_COLOR = new Key("point.error.paint"); //$NON-NLS-1$
	/** Key for specifying an instance either of
	{@link de.erichseifert.gral.plots.colors.ColorMapper} or
	{@link java.awt.Paint} that will be used to paint the error bars. */
	Key ERROR_SHAPE = new Key("point.error.shape"); //$NON-NLS-1$
	/** Key for specifying the {@link java.awt.Stroke} instance defining the
	error bars. */
	Key ERROR_STROKE = new Key("point.error.stroke"); //$NON-NLS-1$

	/**
	 * Returns the shape which is used to draw the point.
	 * @return {@code Shape} instance for the point.
	 */
	Shape getShape();

	/**
	 * Sets the shape which will be used to draw the point.
	 * @param shape {@code Shape} instance for the point.
	 */
	void setShape(Shape shape);

	/**
	 * Returns a mapping that is used to fill the point shapes.
	 * @return {@code ColorMapper} that is used to fill the point shapes.
	 */
	ColorMapper getColor();

	/**
	 * Sets the mapping that will be used to fill the point shapes.
	 * @param color {@code ColorMapper} instance to fill the point shapes.
	 */
	void setColor(ColorMapper color);

	/**
	 * Sets the paint that will be used to fill the point shapes.
	 * @param color {@code Paint} instance to fill the point shapes.
	 */
	void setColor(Paint color);

	/**
	 * Returns whether the data value of a point is displayed or not.
	 * @return {@code true} when the value is displayed, otherwise
	 * {@code false}.
	 */
	boolean isValueDisplayed();

	/**
	 * Returns whether the data value of a point will be displayed or not.
	 * @param valueDisplayed {@code true} if the value should be displayed,
	 * otherwise {@code false}.
	 */
	void setValueDisplayed(boolean valueDisplayed);

	/**
	 * Returns the index of the column that contains the displayed values.
	 * @return Index of the column that contains the displayed values.
	 */
	int getValueColumn();

	/**
	 * Sets the index of the column that contains the displayed values.
	 * @param columnIndex Index of the column that contains the displayed
	 * values.
	 */
	void setValueColumn(int columnIndex);

	/**
	 * Returns the format that is used to render the displayed data values.
	 * @return {@code Format} instance that is used to render the displayed
	 * data values.
	 */
	Format getValueFormat();

	/**
	 * Sets the format that will be used to render the displayed data values.
	 * @param format {@code Format} instance that will be used to render the
	 * displayed data values.
	 */
	void setValueFormat(Format format);

	/**
	 * Returns the current positioning of the data value relative to the data
	 * point.
	 * @return Current positioning of the data value relative to the data
	 * point.
	 */
	Location getValueLocation();

	/**
	 * Sets the positioning of the data value relative to the data point.
	 * @param location Positioning of the data value relative to the data point.
	 */
	void setValueLocation(Location location);

	/**
	 * Returns the relative horizontal position of the value. The position will
	 * be between 0 and 1.
	 * @return Relative horizontal position of the value.
	 */
	double getValueAlignmentX();

	/**
	 * Sets the relative horizontal position of the value. The position can be
	 * specified between 0 and 1.
	 * @param alignmentX Relative horizontal position of the value.
	 */
	void setValueAlignmentX(double alignmentX);

	/**
	 * Returns the relative vertical position of the value. The position will
	 * be between 0 and 1.
	 * @return Relative vertical position of the value.
	 */
	double getValueAlignmentY();

	/**
	 * Sets the relative vertical position of the value. The position can be
	 * specified between 0 and 1.
	 * @param alignmentX Relative vertical position of the value.
	 */
	void setValueAlignmentY(double alignmentX);

	/**
	 * Returns the current rotation angle of the value.
	 * @return Rotation angle in degrees.
	 */
	double getValueRotation();

	/**
	 * Sets the rotation angle of the value.
	 * @param rotation Rotation angle in degrees.
	 */
	void setValueRotation(double angle);

	/**
	 * Returns the current distance of values to the point. The distance is
	 * specified relative to the font height.
	 * @return Distance relative to the font height.
	 */
	double getValueDistance();

	/**
	 * Sets the distance of values to the point. The distance is specified
	 * relative to the font height.
	 * @param distance Distance relative to the font height.
	 */
	void setValueDistance(double distance);

	/**
	 * Returns the mapping that is used to fill the value.
	 * @return {@code ColorMapper} instance that is used to fill the value.
	 */
	ColorMapper getValueColor();

	/**
	 * Sets the mapping that will be used to fill the value.
	 * @param color {@code ColorMapper} instance that will be used to fill
	 * the value.
	 */
	void setValueColor(ColorMapper color);

	/**
	 * Sets the paint that will be used to fill the value.
	 * @param color {@code Paint} instance that will be used to fill the
	 * value.
	 */
	void setValueColor(Paint color);

	/**
	 * Returns the font that is used to render the value.
	 * @return Font that is used to render the value.
	 */
	Font getValueFont();

	/**
	 * Sets the font that will be used to render the value.
	 * @param font Font that will be used to render the value.
	 */
	void setValueFont(Font font);

	/**
	 * Returns whether the error value is displayed.
	 * @return {@code true} if the error value is displayed, otherwise
	 * {@code false}.
	 */
	boolean isErrorDisplayed();

	/**
	 * Sets whether the error value will be displayed.
	 * @param errorDisplayed {@code true} if the error value should be
	 * displayed, otherwise {@code false}.
	 */
	void setErrorDisplayed(boolean errorDisplayed);

	/**
	 * Returns the index of the column that contains the upper error value.
	 * @return Index of the column that contains the upper error value.
	 */
	int getErrorColumnTop();

	/**
	 * Sets the index of the column that contains the upper error value.
	 * @param columnIndex  Index of the column that contains the upper error
	 * value.
	 */
	void setErrorColumnTop(int columnIndex);

	/**
	 * Returns a {@code Shape} instance that can be used for further
	 * calculations.
	 * @param data Information on axes, renderers, and values.
	 * @return Outline that describes the point's shape.
	 */
	Shape getPointShape(PointData data);

	/**
	 * Returns the graphical representation to be drawn for the specified data
	 * value.
	 * @param data Information on axes, renderers, and values.
	 * @param shape Outline that describes the point's shape.
	 * @return Component that can be used to draw the point.
	 */
	Drawable getPoint(PointData data, Shape shape);

	/**
	 * Returns a graphical representation of the value label to be drawn for
	 * the specified data value.
	 * @param data Information on axes, renderers, and values.
	 * @param shape Outline that describes the bounds for the value label.
	 * @return Component that can be used to draw the value label.
	 */
	Drawable getValue(PointData data, Shape shape);
}
