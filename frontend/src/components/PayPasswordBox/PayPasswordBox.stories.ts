import type { Meta, StoryObj } from '@storybook/react';

import PayPasswordBox from './PayPasswordBox';

const meta = {
  title: 'Common/PayPasswordBox',
  component: PayPasswordBox,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {},
} satisfies Meta<typeof PayPasswordBox>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Main: Story = {
  args: {},
};
