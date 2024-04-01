import type { Meta, StoryObj } from '@storybook/react';

import AccountBox from './AccountBox';

const meta = {
  title: 'Common/AccountBox',
  component: AccountBox,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {},
} satisfies Meta<typeof AccountBox>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Main: Story = {
  args: {},
};
