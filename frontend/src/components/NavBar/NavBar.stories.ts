import type { Meta, StoryObj } from '@storybook/react';

import NavBar from './NavBar';

const meta = {
  title: 'Common/NavBar',
  component: NavBar,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    mode: {
      description:
        '하단 바입니다. 부모 타입과 아이 타입이 있습니다',
    }
  },
} satisfies Meta<typeof NavBar>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Parent: Story = {
  args: {
    mode: 'PARENT',
  },
};

export const Child: Story = {
  args: {
    mode: 'CHILD',
  },
};

