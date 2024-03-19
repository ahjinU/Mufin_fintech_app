import type { Meta, StoryObj } from '@storybook/react';

import DealList from './DealList';

const meta = {
  title: 'Common/DealList',
  component: DealList,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    mode: {
      description: 'MODIFY 수정 모드와 READONLY 모드가 있습니다.',
    },
    deals: {
      description: '영수증 내역 list입니다.',
    },
  },
} satisfies Meta<typeof DealList>;

export default meta;
type Story = StoryObj<typeof meta>;

export const READONLY: Story = {
  args: {
    mode: 'READONLY',
    deals: [
      {
        name: '츕파츕스',
        count: 2,
        totalPrice: 10000,
      },
      {
        name: '스윙칩',
        count: 2,
        totalPrice: 10000,
      },
    ],
  },
};

export const MODIFY: Story = {
  args: {
    mode: 'MODIFY',
    deals: [
      {
        name: '츕파츕스',
        count: 2,
        totalPrice: 10000,
      },
      {
        name: '스윙칩',
        count: 2,
        totalPrice: 10000,
      },
    ],
  },
};
