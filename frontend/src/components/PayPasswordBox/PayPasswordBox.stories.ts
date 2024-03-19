import type { Meta, StoryObj } from '@storybook/react';

import PayPasswordBox from './PayPasswordBox';

const meta = {
  title: 'Common/PayPasswordBox',
  component: PayPasswordBox,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    isOpen: {
      description: '열린지 닫힌지 상태를 판별합니다.',
    },
  },
} satisfies Meta<typeof PayPasswordBox>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Main: Story = {
  args: {
    isOpen: true,
  },
};
